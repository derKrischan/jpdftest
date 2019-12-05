# JPdfTest

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.derkrischan/jpdftest/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.derkrischan/jpdftest)
[![CircleCI](https://circleci.com/gh/derKrischan/jpdftest/tree/master.svg?style=svg)](https://circleci.com/gh/derKrischan/jpdftest/tree/master)

This is a small library that will eventually be extended to test PDF contents programmatically. The focus is not on a per pixel comparison of images but to deliver some asserters to assure that some specific text/image is located in a certain area. 

## Table of contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [JPdfTest](#JPdfTest)
	- [Table of contents](#Table-of-contents)
	- [Design principles](#Design-principles)
	- [Features](#Features)
	- [Tech](#Tech)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Design principles

The library is designed after the principles of fluent assertions. Every assertion returns the current or a new asserter. This is useful for improved test readability.

An example might be:

	PdfAssertions.assertThat("myDocument.pdf")
		.pageCount().isBetween(41, 42)
		.page(1).textInRegion(MetricRectangle.create(120, 23, 39, 10)).chomp().matches("Hello world")
		.page(2).containsImage("resources/my_diagram.png")
		.document().author().isNotBlank().matches("Jon Doe")
		.document().producer().startsWith("LibreOffice");
		
Asserters may be used to traverse from documents to single pages and back to the whole document. 

## Features

* Assert that a PDF document contains a specified text
* Assert that a specified text is in a certain region
* Verify PDF document page count 
* Assert that a specific image is embedded at a certain page
* Assert that a specific image is in a certain area of a page
* Assert that a PDF document is compliant to PDF/A-1b standard
* Check the author of a PDF document
* Check the creator of a PDF document
* Check the subject of a PDF document
* Check the title of a PDF document
* Check the producer of a PDF document
* Check the creation date of a PDF document
* Check the document version

## Tech

* The project uses [PdfBox](https://pdfbox.apache.org/) which tries to create a font cache in system temp directory. In case the user has no write access for that directory, an error occurs. In order to specify the font cache directory use the property "pdfbox.fontcache" via command line. E.g. :

	java -Dpdfbox.fontcache=path/to/cache ...
