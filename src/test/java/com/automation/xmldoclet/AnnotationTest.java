package com.automation.xmldoclet;

import com.automation.xmldoclet.simpledata.Annotation1;
import com.automation.xmldoclet.simpledata.Annotation2;
import com.automation.xmldoclet.simpledata.Annotation3;
import com.automation.xmldoclet.xjc.Annotation;
import com.automation.xmldoclet.xjc.AnnotationElement;
import com.automation.xmldoclet.xjc.AnnotationInstance;
import com.automation.xmldoclet.xjc.Package;
import com.automation.xmldoclet.xjc.Root;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test group for Annotations
 */
@SuppressWarnings("deprecation")
public class AnnotationTest extends AbstractTestParent {
    /**
     * Rigorous Parser :-)
     */
    @Test
    public void testSampledoc() {
        executeJavadoc(null, new String[] {"./src/test/java"}, null, null, new String[] {"com"},
            new String[] {"-dryrun"});
    }

    /**
     * testing an annotation with nothing defined
     */
    @Test
    public void testAnnotation1() {
        String[] sourceFiles = new String[] {getSourceFile(Annotation1.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Annotation annotationNode = packageNode.getAnnotation().get(0);

        assertEquals(rootNode.getPackage().size(), 1);
        assertNull(packageNode.getComment());
        assertEquals(packageNode.getName(), getSimpledataPackage());
        assertEquals(packageNode.getAnnotation().size(), 1);
        assertEquals(packageNode.getEnum().size(), 0);
        assertEquals(packageNode.getInterface().size(), 0);
        assertEquals(packageNode.getClazz().size(), 0);

        assertEquals(annotationNode.getComment(), "Annotation1");
        assertEquals(annotationNode.getName(), Annotation1.class.getSimpleName());
        assertEquals(annotationNode.getQualified(), Annotation1.class.getName());
        assertEquals(annotationNode.getScope(), "public");
        assertEquals(annotationNode.getAnnotation().size(), 0);
        assertEquals(annotationNode.getElement().size(), 0);
        assertTrue(annotationNode.isIncluded());
    }

    /**
     * testing an annotation with an annotation decorating it
     */
    @Test
    public void testAnnotation2() {
        String[] sourceFiles = new String[] {getSourceFile(Annotation2.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Annotation annotationNode = packageNode.getAnnotation().get(0);
        AnnotationInstance annotationInstance = annotationNode.getAnnotation().get(0);

        assertEquals(rootNode.getPackage().size(), 1);
        assertNull(packageNode.getComment());
        assertEquals(packageNode.getName(), getSimpledataPackage());
        assertEquals(packageNode.getAnnotation().size(), 1);
        assertEquals(packageNode.getEnum().size(), 0);
        assertEquals(packageNode.getInterface().size(), 0);
        assertEquals(packageNode.getClazz().size(), 0);

        assertEquals(annotationNode.getComment(), "Annotation2");
        assertEquals(annotationNode.getName(), Annotation2.class.getSimpleName());
        assertEquals(annotationNode.getQualified(), Annotation2.class.getName());
        assertEquals(annotationNode.getScope(), "public");
        assertEquals(annotationNode.getAnnotation().size(), 1);
        assertEquals(annotationNode.getElement().size(), 0);
        assertTrue(annotationNode.isIncluded());

        // test annotation 'deprecated' on class
        assertEquals(annotationInstance.getQualified(), "java.lang.Deprecated");
        assertEquals(annotationInstance.getName(), "Deprecated");
        assertEquals(annotationInstance.getArgument().size(), 0);
    }

    /**
     * testing an annotation with one element field
     */
    @Test
    public void testAnnotation3() {
        String[] sourceFiles = new String[] {getSourceFile(Annotation3.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Annotation annotationNode = packageNode.getAnnotation().get(0);
        AnnotationElement element = annotationNode.getElement().get(0);

        assertEquals(rootNode.getPackage().size(), 1);
        assertNull(packageNode.getComment());
        assertEquals(packageNode.getName(), getSimpledataPackage());
        assertEquals(packageNode.getAnnotation().size(), 1);
        assertEquals(packageNode.getEnum().size(), 0);
        assertEquals(packageNode.getInterface().size(), 0);
        assertEquals(packageNode.getClazz().size(), 0);

        assertEquals(annotationNode.getComment(), "Annotation3");
        assertEquals(annotationNode.getName(), Annotation3.class.getSimpleName());
        assertEquals(annotationNode.getQualified(), Annotation3.class.getName());
        assertEquals(annotationNode.getScope(), "public");
        assertEquals(annotationNode.getAnnotation().size(), 0);
        assertEquals(annotationNode.getElement().size(), 1);
        assertTrue(annotationNode.isIncluded());

        // test annotation element
        assertEquals(element.getName(), "id");
        assertEquals(element.getQualified(), getSimpledataPackage() + ".Annotation3.id");
        assertEquals(element.getType().getQualified(), "int");
        assertEquals(element.getDefault(), Integer.toString(3));
    }

    /**
     * testing an annotation with non-public definition
     */
    @Test
    public void testAnnotation4() {
        String[] sourceFiles = new String[] {getSourceFile("Annotation4")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Annotation annotationNode = packageNode.getAnnotation().get(0);

        assertEquals(rootNode.getPackage().size(), 1);
        assertNull(packageNode.getComment());
        assertEquals(packageNode.getName(), getSimpledataPackage());
        assertEquals(packageNode.getAnnotation().size(), 1);
        assertEquals(packageNode.getEnum().size(), 0);
        assertEquals(packageNode.getInterface().size(), 0);
        assertEquals(packageNode.getClazz().size(), 0);

        assertEquals(annotationNode.getComment(), "Annotation4");
        assertEquals(annotationNode.getName(), "Annotation4");
        assertEquals(annotationNode.getQualified(), getSimpledataPackage() + ".Annotation4");
        assertEquals(annotationNode.getScope(), "");
        assertEquals(annotationNode.getAnnotation().size(), 0);
        assertEquals(annotationNode.getElement().size(), 0);
        assertTrue(annotationNode.isIncluded());
    }
}