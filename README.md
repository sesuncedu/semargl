Welcome to the home of Semargl!
===============================

Semargl is a modular framework for crawling [linked data](http://en.wikipedia.org/wiki/Linked_data)
from structured documents. The main goal of the project is to provide lightweight
and performant tool without excess dependencies.

At this moment Semargl offers high-performant streaming parsers for RDF/XML,
[RDFa](http://en.wikipedia.org/wiki/Rdfa), N-Triples, JSON-LD,
streaming serializers for Turtle, NTriples, NQuads and integration with Jena, Clerezza and Sesame.

Small memory footprint, and CPU requirements allow framework to be embedded in any system.
It runs seamlessly on Android and GAE.

You can check some framework capabilities via [RDFa parser demo](http://demo.semarglproject.org).

[![Build Status](https://travis-ci.org/levkhomich/semargl.png?branch=master)](https://travis-ci.org/levkhomich/semargl)
[![Coverage Status](https://coveralls.io/repos/levkhomich/semargl/badge.png?branch=master)](https://coveralls.io/r/levkhomich/semargl?branch=master)

Why use Semargl?
================

Lightweight
-----------

Semargl’s code is small and simple to understand. It has no external dependencies and
it will never [read a mail](http://en.wikipedia.org/wiki/Zawinski's_law_of_software_envelopment).
Internally it operates with a raw strings and creates as few objects as possible,
so your Android or GAE applications will be happy.

Standard conformant
-------------------

All parsers and serializers fully support
[corresponding W3C specifications](http://semarglproject.org/conformance.html) and test suites.

Dead Simple
-----------

No jokes!

```xml
<dependency>
    <groupId>org.semarglproject</groupId>
    <artifactId>semargl-rdfa</artifactId>
    <version>0.6.1</version>
</dependency>
```

```java
// just init triple store you want
MGraph graph = ... // Clerezza calls
// create processing pipe
StreamProcessor sp = new StreamProcessor(NTriplesParser.connect(ClerezzaSink.connect(graph));
// and run it
sp.process(file, docUri);
```

If you want to use Semargl as a standalone framework, you can find useful internal
serializers and easily extendable API. See more info and usage examples at
[project's page](http://semarglproject.org/usage.html).

Build
=====

To build framework just run `mvn clean install`. RDFa tests require direct Internet connection.