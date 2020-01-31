[![Build Status](https://travis-ci.org/web-devel/cuba-component-data-generator.svg?branch=master)](https://travis-ci.org/web-devel/cuba-component-data-generator)

# CUBA Component - Data Generator

Application component which lets you generate sample data (persistent entities) via user interface.
May be useful for demo and test projects as well as for real projects when it's not possible to retrieve a dump.

![showcase](https://raw.githubusercontent.com/web-devel/cuba-component-data-generator/master/etc/showcase.gif)

## Features

* Different generation strategies:
  * Random 
  * Manual
  * [Faker](https://github.com/serpro69/kotlin-faker) is used for conscious strings generation. See [data-providers](https://github.com/serpro69/kotlin-faker#data-providers).
* Batch generation.
* Browse and remove generated entities.

## Installation

The add-on is available on [marketplace](https://www.cuba-platform.com/marketplace).
Best install [using](https://doc.cuba-platform.com/studio/#add_ons) CUBA Studio interface:
  - In main menu: CUBA -> Marketplace
  - Search by `Data Generator`
  - Click `Install`
  
### Compatibility

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 7.2.x            | 0.3.x          |
| 7.1.x            | 0.3.x          |

## Usage

In main menu open Administration -> Data Generation -> Generate Entities.


### Supported Attribute Types

* DataTypes
  * String
  * Boolean
  * Integer
  * Long
  * Double
  * BigDecimal
* Enum
* References
  * toOne
