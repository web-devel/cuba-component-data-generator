package com.haulmont.addon.datagen.web.screens.genarate

import com.haulmont.addon.datagen.entity.DataGenerationCommand
import com.haulmont.addon.datagen.entity.EntityGenerationSettings
import com.haulmont.addon.datagen.entity.PropertyGenerationSettings
import com.haulmont.addon.datagen.entity.bool.BooleanPropertyGenerationSettings
import com.haulmont.addon.datagen.entity.enm.EnumPropGenSettings
import com.haulmont.addon.datagen.entity.number.NumberPropGenSettings
import com.haulmont.addon.datagen.entity.str.StringPropGenSettings
import com.haulmont.addon.datagen.generation.GenerationSettingsFactory
import com.haulmont.addon.datagen.service.DataGenerationService
import com.haulmont.addon.datagen.web.screens.generationresult.GenerationResultScreen
import com.haulmont.addon.datagen.web.screens.props.PropGenFragment
import com.haulmont.addon.datagen.web.screens.props.booleanpropertygenerationsettings.BooleanPropertyGenerationSettingsFragment
import com.haulmont.addon.datagen.web.screens.props.enumpropgensettings.EnumPropGenSettingsFragment
import com.haulmont.addon.datagen.web.screens.props.numberpropgensettings.NumberPropGenSettingsFragment
import com.haulmont.addon.datagen.web.screens.props.stringpropertygenerationsettings.StringPropertyGenerationSettingsFragment
import com.haulmont.chile.core.model.MetaClass
import com.haulmont.chile.core.model.MetaProperty
import com.haulmont.cuba.core.app.serialization.EntitySerializationAPI
import com.haulmont.cuba.core.app.serialization.EntitySerializationOption
import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.core.global.MetadataTools
import com.haulmont.cuba.gui.Fragments
import com.haulmont.cuba.gui.ScreenBuilders
import com.haulmont.cuba.gui.UiComponents
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.data.options.ListOptions
import com.haulmont.cuba.gui.model.CollectionPropertyContainer
import com.haulmont.cuba.gui.model.DataComponents
import com.haulmont.cuba.gui.model.InstanceContainer
import com.haulmont.cuba.gui.screen.*
import com.haulmont.cuba.web.gui.components.WebVBoxLayout
import javax.inject.Inject

@Suppress("IncorrectCreateEntity")
@UiController("datagen_Generation")
@UiDescriptor("data-generation.xml")
class DataGenerationScreen : Screen() {

    // Services
    @Inject
    private lateinit var metadata: Metadata
    @Inject
    private lateinit var uiComponents: UiComponents
    @Inject
    private lateinit var dataComponents: DataComponents
    @Inject
    private lateinit var fragments: Fragments
    @Inject
    private lateinit var dataGenerationService: DataGenerationService
    @Inject
    private lateinit var metadataTools: MetadataTools
    @Inject
    private lateinit var entitySerializationAPI: EntitySerializationAPI

    // Data
    @Inject
    private lateinit var generationCommandDc: InstanceContainer<DataGenerationCommand<Entity<*>>>
    @Inject
    private lateinit var propertiesDc: CollectionPropertyContainer<PropertyGenerationSettings>

    // UI
    @Inject
    private lateinit var entityLookup: LookupField<MetaClass>
    @Inject
    private lateinit var propertiesConfigBox: ScrollBoxLayout
    @Inject
    private lateinit var previewTextArea: TextArea<String>
    @Inject
    private lateinit var generationSettingsBox: GroupBoxLayout
    @Inject
    private lateinit var previewBox: VBoxLayout
    @Inject
    private lateinit var screenBuilders: ScreenBuilders

    @Subscribe
    private fun onInit(event: InitEvent) {
        generationCommandDc.setItem(DataGenerationCommand())
        updateEntitySelectLookup()
    }

    @Subscribe("generateBtn")
    private fun onGenerateBtnClick(event: Button.ClickEvent) {
        val resultScreen: GenerationResultScreen = screenBuilders.screen(this)
                .withScreenClass(GenerationResultScreen::class.java)
                .withLaunchMode(OpenMode.DIALOG)
                .build()

        resultScreen.command = this.generationCommandDc.item
        resultScreen.show()
    }


    @Subscribe("refreshPreviewBtn")
    private fun onRefreshPreviewBtnClick(event: Button.ClickEvent) {
        updatePreview()
    }

    private fun updateEntitySelectLookup() {
        val nonSystemMetaClasses = metadataTools.allPersistentMetaClasses
                .filter { !metadataTools.isSystemLevel(it) }
                .sortedBy { it.name }

        val systemMetaClasses = metadataTools.allPersistentMetaClasses
                .filter { metadataTools.isSystemLevel(it) }
                .sortedBy { it.name }

        entityLookup.options = ListOptions(nonSystemMetaClasses + systemMetaClasses)
        entityLookup.addValueChangeListener { handleMetaClassSelection(it.value) }
    }

    private fun handleMetaClassSelection(metaClass: MetaClass?) {
        propertiesConfigBox.removeAll()
        if (metaClass == null) {
            generationCommandDc.item.entityGenerationSettings = null
            previewBox.isVisible = false
            generationSettingsBox.isVisible = false
            return
        }
        val entityGenerationSettings = EntityGenerationSettings<Entity<*>>()
        entityGenerationSettings.entityClass = metaClass.getJavaClass<Entity<*>>()
        generationCommandDc.item.entityGenerationSettings = entityGenerationSettings
        previewBox.isVisible = true
        generationSettingsBox.isVisible = true

        metaClass.properties
                .filter { shouldBeGenerated(it) }
                .forEach {
                    val propSettings = GenerationSettingsFactory.createSettings(it) ?: return
                    entityGenerationSettings.properties.add(propSettings)
                    propertiesConfigBox.add(createPropertyGenerationUI(it, propSettings))
                }
        updatePreview()
    }

    private fun createPropertyGenerationUI(
            prop: MetaProperty,
            settings: PropertyGenerationSettings
    ): Component {
        val propDC = dataComponents.createInstanceContainer(
                PropertyGenerationSettings::class.java,
                propertiesDc,
                prop.name.toString()
        )
        propDC.setItem(settings)
        screenData.registerContainer("${prop.name}Dc", propDC) // todo unregister

        val propertySettingsBox = uiComponents.create<WebVBoxLayout>(WebVBoxLayout::class.java)
        propertySettingsBox.setWidth("100%")

        val propCaption = uiComponents.create(Label.TYPE_STRING)
        propCaption.value = prop.name
        propCaption.styleName = "bold"
        propertySettingsBox.add(propCaption)

        val propFragment = createPropFragment(settings)
        propFragment.addDcPropChangeListener { e -> updatePreview() }
        propertySettingsBox.add(propFragment.fragment)
        return propertySettingsBox
    }

    private fun createPropFragment(propSettings: PropertyGenerationSettings): PropGenFragment<*> {
        return when (propSettings) {
            is BooleanPropertyGenerationSettings ->
                fragments.create(this, BooleanPropertyGenerationSettingsFragment::class.java)
                        .setItem(propSettings)
            is StringPropGenSettings ->
                fragments.create(this, StringPropertyGenerationSettingsFragment::class.java)
                        .setItem(propSettings)
            is NumberPropGenSettings ->
                fragments.create(this, NumberPropGenSettingsFragment::class.java)
                        .setItem(propSettings)
            is EnumPropGenSettings ->
                fragments.create(this, EnumPropGenSettingsFragment::class.java)
                        .setItem(propSettings)
            else -> throw IllegalStateException("Unsupported Property")
        }
    }


    private fun shouldBeGenerated(it: MetaProperty) =
            GenerationSettingsFactory.isGeneratorAvailable(it)
                    && !metadata.tools.isSystem(it)
                    && metadata.tools.isPersistent(it)

    private fun updatePreview() {
        val entityGenerationSettings = generationCommandDc.item.entityGenerationSettings
        if (entityGenerationSettings == null) {
            previewTextArea.value = null
            return
        }

        val entity = dataGenerationService.generateEntity(entityGenerationSettings)
        val entityJson = entitySerializationAPI.toJson(entity, null, EntitySerializationOption.PRETTY_PRINT)
        previewTextArea.value = entityJson
    }
}
