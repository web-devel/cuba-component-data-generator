[![Build Status](https://travis-ci.org/web-devel/cuba-component-data-generator.svg?branch=master)](https://travis-ci.org/web-devel/cuba-component-data-generator)

# CUBA Component - Data Generator

Application component which lets you generate sample data (persistent entities).
May be useful for demo and test projects as well as for real projects when it's not possible to retrieve a dump.

## Installation

Add-on coordinates:
```
com.haulmont.addon.datagen:datagen-global:{latest version}
```

https://doc.cuba-platform.com/studio/#add_ons

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 7.1.x            | 0.1.x          |

### Usage

In main menu open Administration -> Data Generation -> Generate Entities.

### Dependencies

[Faker](https://github.com/serpro69/kotlin-faker) is used to generate conscious strings.

Add the Faker repository to the `build.gradle`:
```groovy
    maven {
        url 'https://dl.bintray.com/serpro69/maven/'
    }
```

### Supported Attribute Types

* DataTypes
  * String
  * Boolean
  * Integer
  * Long
  * Double
  * BigDecimal
* Enum
