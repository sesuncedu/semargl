/**
 * Copyright 2012-2013 the Semargl contributors. See AUTHORS for more details.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.semarglproject.jena;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.semarglproject.jena.core.sink.JenaSink;
import org.semarglproject.rdf.rdfa.RdfaTestSuiteHelper;
import org.semarglproject.source.StreamProcessor;
import org.semarglproject.rdf.ParseException;
import org.semarglproject.rdf.rdfa.RdfaParser;
import org.semarglproject.rdf.rdfa.RdfaTestSuiteHelper.TestCase;
import org.semarglproject.vocab.RDFa;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

import static org.semarglproject.rdf.rdfa.RdfaTestSuiteHelper.SaveToFileCallback;
import static org.semarglproject.rdf.rdfa.RdfaTestSuiteHelper.runTestBundle;

public final class JenaRdfaParserTest {

    private Model model;
    private StreamProcessor streamProcessor;
    private SaveToFileCallback jenaCallback = new SaveToFileCallback() {
        @Override
        public void run(Reader input, String inputUri, Writer output, short rdfaVersion) throws ParseException {
            streamProcessor.setProperty(RdfaParser.RDFA_VERSION_PROPERTY, rdfaVersion);
            try {
                streamProcessor.process(input, inputUri);
            } finally {
                model.write(output, "TURTLE");
            }
        }

        @Override
        public String getOutputFileExt() {
            return "ttl";
        }
    };

    @BeforeClass
    public void init() throws SAXException {
        model = ModelFactory.createDefaultModel();

        streamProcessor = new StreamProcessor(RdfaParser.connect(JenaSink.connect(model)));
        streamProcessor.setProperty(RdfaParser.ENABLE_VOCAB_EXPANSION, true);
    }

    @BeforeMethod
    public void setUp() {
        model.removeAll();
    }

    @DataProvider
    public static Object[][] getRdfa10Xhtml1TestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.0", "xhtml1"));
    }

    @DataProvider
    public static Object[][] getRdfa10SvgTestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.0", "svg"));
    }

    @DataProvider
    public static Object[][] getRdfa11Html4TestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.1", "html4"));
    }

    @DataProvider
    public static Object[][] getRdfa11Xhtml1TestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.1", "xhtml1"));
    }

    @DataProvider
    public static Object[][] getRdfa11Html5TestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.1", "html5"));
    }

    @DataProvider
    public static Object[][] getRdfa11XmlTestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.1", "xml"));
    }

    @DataProvider
    public static Object[][] getRdfa11SvgTestSuite() {
        return convertToDataProvider(RdfaTestSuiteHelper.getTestSuite("rdfa1.1", "svg"));
    }

    private static Object[][] convertToDataProvider(Collection<TestCase> tests) {
        Object[][] result = new Object[tests.size()][];
        int i = 0;
        for (TestCase testCase : tests) {
            result[i++] = new Object[]{testCase};
        }
        return result;
    }

    @Test(dataProvider = "getRdfa10Xhtml1TestSuite")
    public void runRdfa10Xhtml1Tests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_10);
    }

    @Test(dataProvider = "getRdfa10SvgTestSuite")
    public void runRdfa10SvgTests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_10);
    }

    @Test(dataProvider = "getRdfa11Html4TestSuite")
    public void runRdfa11Html4Tests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_11);
    }

    @Test(dataProvider = "getRdfa11Xhtml1TestSuite")
    public void runRdfa11Xhtml1Tests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_11);
    }

    @Test(dataProvider = "getRdfa11Html5TestSuite")
    public void runRdfa11Html5Tests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_11);
    }

    @Test(dataProvider = "getRdfa11XmlTestSuite")
    public void runRdfa11XmlTests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_11);
    }

    @Test(dataProvider = "getRdfa11SvgTestSuite")
    public void runRdfa11SvgTests(TestCase testCase) {
        runTestBundle(testCase, jenaCallback, RDFa.VERSION_11);
    }

}
