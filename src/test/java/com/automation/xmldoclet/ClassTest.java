package com.automation.xmldoclet;

import com.automation.xmldoclet.simpledata.Annotation3;
import com.automation.xmldoclet.simpledata.AnnotationCascadeChild;
import com.automation.xmldoclet.simpledata.Class1;
import com.automation.xmldoclet.simpledata.Class13;
import com.automation.xmldoclet.simpledata.Class14;
import com.automation.xmldoclet.simpledata.Class15;
import com.automation.xmldoclet.simpledata.Class16;
import com.automation.xmldoclet.simpledata.Class17;
import com.automation.xmldoclet.simpledata.Class18;
import com.automation.xmldoclet.simpledata.Class19;
import com.automation.xmldoclet.simpledata.Class2;
import com.automation.xmldoclet.simpledata.Class20;
import com.automation.xmldoclet.simpledata.Class21;
import com.automation.xmldoclet.simpledata.Class22;
import com.automation.xmldoclet.simpledata.Class23;
import com.automation.xmldoclet.simpledata.Class24;
import com.automation.xmldoclet.simpledata.Class3;
import com.automation.xmldoclet.simpledata.Class4;
import com.automation.xmldoclet.simpledata.Class5;
import com.automation.xmldoclet.simpledata.Class6;
import com.automation.xmldoclet.simpledata.Class7;
import com.automation.xmldoclet.simpledata.Class8;
import com.automation.xmldoclet.simpledata.Class9;
import com.automation.xmldoclet.simpledata.ClassAnnotationCascade;
import com.automation.xmldoclet.simpledata.Enum1;
import com.automation.xmldoclet.xjc.AnnotationArgument;
import com.automation.xmldoclet.xjc.AnnotationInstance;
import com.automation.xmldoclet.xjc.Class;
import com.automation.xmldoclet.xjc.Constructor;
import com.automation.xmldoclet.xjc.Field;
import com.automation.xmldoclet.xjc.Method;
import com.automation.xmldoclet.xjc.Package;
import com.automation.xmldoclet.xjc.Root;
import com.automation.xmldoclet.xjc.TypeInfo;
import com.automation.xmldoclet.xjc.TypeParameter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test group for Classes
 */
@SuppressWarnings("deprecation")
public class ClassTest extends AbstractTestParent {

    /**
     * Testing nested Annotations
     *
     * @see ClassAnnotationCascade
     */
    @Test
    public void testClassAnnotationCascade() {
        String[] sourceFiles = new String[] {getSourceFile(ClassAnnotationCascade.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(1, packageNode.getClazz().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());

        assertEquals("ClassAnnotationCascade", classNode.getComment());
        assertEquals("ClassAnnotationCascade", classNode.getName());

        assertEquals(classNode.getQualified(), ClassAnnotationCascade.class.getName());

        assertEquals(1, classNode.getAnnotation().size());
        AnnotationInstance annotationNode = classNode.getAnnotation().get(0);

        assertEquals("AnnotationCascade", annotationNode.getName());
        assertEquals(1, annotationNode.getArgument().size());

        AnnotationArgument annotationArgNode = annotationNode.getArgument().get(0);

        // Two nested annotations in child attribute
        assertEquals("children", annotationArgNode.getName());
        assertEquals(0, annotationArgNode.getValue().size());
        assertEquals(2, annotationArgNode.getAnnotation().size());

        AnnotationInstance annonNodePrimitive = annotationArgNode.getAnnotation().get(0);
        AnnotationInstance annonNodeNested = annotationArgNode.getAnnotation().get(1);

        // Equal attribs
        assertEquals(AnnotationCascadeChild.class.getSimpleName(), annonNodePrimitive.getName());
        assertEquals(AnnotationCascadeChild.class.getSimpleName(), annonNodeNested.getName());
        assertEquals(AnnotationCascadeChild.class.getName(), annonNodePrimitive.getQualified());
        assertEquals(AnnotationCascadeChild.class.getName(), annonNodeNested.getQualified());
        assertEquals(2, annonNodePrimitive.getArgument().size());
        assertEquals(2, annonNodeNested.getArgument().size());
        assertEquals("name", annonNodePrimitive.getArgument().get(0).getName());
        assertEquals("name", annonNodeNested.getArgument().get(0).getName());

        // Primitive
        AnnotationArgument annArgNodePrimitive = annonNodePrimitive.getArgument().get(1);
        assertEquals("dummyData", annArgNodePrimitive.getName());
        assertEquals("java.lang.String", annArgNodePrimitive.getType().getQualified());
        assertEquals(0, annArgNodePrimitive.getAnnotation().size());
        assertEquals(3, annArgNodePrimitive.getValue().size());
        assertEquals("A", annArgNodePrimitive.getValue().get(0));
        assertEquals("B", annArgNodePrimitive.getValue().get(1));
        assertEquals("C", annArgNodePrimitive.getValue().get(2));

        // Nested
        AnnotationArgument annArgNodeNested = annonNodeNested.getArgument().get(1);
        assertEquals("subAnnotations", annArgNodeNested.getName());
        assertEquals(Annotation3.class.getName(), annArgNodeNested.getType().getQualified());
        assertEquals(3, annArgNodeNested.getAnnotation().size());
        assertEquals(0, annArgNodeNested.getValue().size());
        assertEquals(Annotation3.class.getSimpleName(), annArgNodeNested.getAnnotation().get(0).getName());
        assertEquals(Annotation3.class.getName(), annArgNodeNested.getAnnotation().get(1).getQualified());
        assertEquals(1, annArgNodeNested.getAnnotation().get(2).getArgument().size());

        assertEquals("666", annArgNodeNested.getAnnotation().get(2).getArgument().get(0).getValue().get(0));
    }


    /**
     * Rigorous Parser :-)
     */
    @Test
    public void testSampledoc() {
        executeJavadoc(".", new String[] {"./src/test/java"}, null, null, new String[] {"com"},
            new String[] {"-dryrun"});
    }

    /**
     * testing a class with nothing defined EMPIRICAL OBSERVATION: The default
     * constructor created by the java compiler is not marked synthetic. um
     * what?
     */
    @Test
    public void testClass1() {
        String[] sourceFiles = new String[] {getSourceFile(Class1.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class1", classNode.getComment());
        assertEquals(Class1.class.getSimpleName(), classNode.getName());
        assertEquals(Class1.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing a class with 1 constructor
     */
    @Test
    public void testClass2() {
        String[] sourceFiles = new String[] {getSourceFile(Class2.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        Constructor constructor = classNode.getConstructor().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class2", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class2.class.getSimpleName(), classNode.getName());
        assertEquals(Class2.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());

        assertEquals("Constructor1", constructor.getComment());
        assertEquals("Class2", constructor.getName());
        assertEquals(0, constructor.getParameter().size());
        assertEquals(0, constructor.getAnnotation().size());
    }

    /**
     * testing a class with 1 method
     */
    @Test
    public void testClass3() {
        String[] sourceFiles = new String[] {getSourceFile(Class3.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        Method method = classNode.getMethod().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class3", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class3.class.getSimpleName(), classNode.getName());
        assertEquals(Class3.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());

        assertEquals("method1", method.getComment());
        assertEquals("method1", method.getName());
        assertEquals("()", method.getSignature());
        assertFalse(method.isFinal());
        assertFalse(method.isNative());
        assertFalse(method.isStatic());
        assertFalse(method.isSynchronized());
        assertFalse(method.isVarArgs());
        assertEquals(getSimpledataPackage() + ".Class3.method1", method.getQualified());
        assertEquals("public", method.getScope());
        assertEquals(0, method.getAnnotation().size());
        assertEquals(0, method.getParameter().size());
        assertEquals(0, method.getException().size());

        TypeInfo returnNode = method.getReturn();
        assertEquals("int", returnNode.getQualified());
        assertNull(returnNode.getDimension());
        assertEquals(0, returnNode.getGeneric().size());
        assertNull(returnNode.getWildcard());
    }

    /**
     * testing a class with 1 field
     */
    @Test
    public void testClass4() {
        String[] sourceFiles = new String[] {getSourceFile(Class4.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        Field field = classNode.getField().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class4", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class4.class.getSimpleName(), classNode.getName());
        assertEquals(Class4.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getField().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());

        // test field
        assertEquals("field1", field.getComment());
        assertEquals("field1", field.getName());
        assertEquals("public", field.getScope());
        assertEquals("int", field.getType().getQualified());
        assertNull(field.getType().getDimension());
        assertEquals(0, field.getType().getGeneric().size());
        assertNull(field.getType().getWildcard());
        assertFalse(field.isStatic());
        assertFalse(field.isTransient());
        assertFalse(field.isVolatile());
        assertFalse(field.isFinal());
        assertNull(field.getConstant());
        assertEquals(0, field.getAnnotation().size());
    }

    /**
     * testing a class that extends another class with 1 method
     */
    @Test
    public void testClass5() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class5.class),
            getSourceFile(Class3.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(2, packageNode.getClazz().size());

        assertEquals("Class5", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class5.class.getSimpleName(), classNode.getName());
        assertEquals(Class5.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(getSimpledataPackage() + ".Class3", classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing a class that implements one interface
     */
    @Test
    public void testClass6() {
        String[] sourceFiles = new String[] {getSourceFile(Class6.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        TypeInfo interfaceNode = classNode.getInterface().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class6", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class6.class.getSimpleName(), classNode.getName());
        assertEquals(Class6.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(1, classNode.getAnnotation().size());
        assertEquals(1, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());

        // the particular interface chosen for this test also will change this
        // flag to true!
        assertTrue(classNode.isSerializable());

        // verify interface
        assertEquals(java.io.Serializable.class.getName(), interfaceNode.getQualified());
    }

    /**
     * testing one annotation instance on the class
     */
    @Test
    public void testClass7() {
        String[] sourceFiles = new String[] {getSourceFile(Class7.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance annotationNode = classNode.getAnnotation().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class7", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class7.class.getSimpleName(), classNode.getName());
        assertEquals(Class7.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(1, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());

        // test annotation 'deprecated' on class
        assertEquals("java.lang.Deprecated", annotationNode.getQualified());
        assertEquals("Deprecated", annotationNode.getName());
        assertEquals(0, annotationNode.getArgument().size());
    }

    /**
     * testing abstract keyword on class
     */
    @Test
    public void testClass8() {
        String[] sourceFiles = new String[] {getSourceFile(Class8.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class8", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class8.class.getSimpleName(), classNode.getName());
        assertEquals(Class8.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertTrue(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing java.io.Externalizable interface on class
     */
    @Test
    public void testClass9() {
        String[] sourceFiles = new String[] {getSourceFile(Class9.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class9", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class9.class.getSimpleName(), classNode.getName());
        assertEquals(Class9.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(2, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(1, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertTrue(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing difference of scope modifier on class
     */
    @Test
    public void testClass10() {
        String[] sourceFiles = new String[] {getSourceFile("Class10")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class10", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class10", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class10", classNode.getQualified());
        assertEquals("", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing if isException is populated correctly
     */
    @Test
    public void testClass11() {
        String[] sourceFiles = new String[] {getSourceFile("Class11")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class11", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class11", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class11", classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(1, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Exception.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertTrue(classNode.isException());
        assertFalse(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing if isError is populated correctly
     */
    @Test
    public void testClass12() {
        String[] sourceFiles = new String[] {getSourceFile("Class12")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class12", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class12", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class12", classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(1, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Error.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertTrue(classNode.isError());
        assertEquals(0, classNode.getGeneric().size());
    }

    /**
     * testing if type variables can be determined
     */
    @Test
    public void testClass13() {
        String[] sourceFiles = new String[] {getSourceFile(Class13.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class13", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class13", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class13", classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var
        assertEquals("Fun", typeParameter.getName());
        assertEquals(0, typeParameter.getBound().size());
    }

    /**
     * testing if a single bounds can be determined
     */
    @Test
    public void testClass14() {
        String[] sourceFiles = new String[] {getSourceFile(Class14.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class14", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class14", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class14", classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var

        assertEquals("Fun", typeParameter.getName());
        assertEquals(1, typeParameter.getBound().size());
        assertEquals(Number.class.getName(), typeParameter.getBound().get(0));
    }

    /**
     * testing if a multiple bounds can be determined
     */
    @Test
    public void testClass15() {
        String[] sourceFiles = new String[] {getSourceFile(Class15.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(getSimpledataPackage(), packageNode.getName());
        assertEquals(0, packageNode.getAnnotation().size());
        assertEquals(0, packageNode.getEnum().size());
        assertEquals(0, packageNode.getInterface().size());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class15", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class15", classNode.getName());
        assertEquals(getSimpledataPackage() + ".Class15", classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertEquals(0, classNode.getMethod().size());
        assertEquals(0, classNode.getField().size());
        assertEquals(0, classNode.getAnnotation().size());
        assertEquals(0, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var
        assertEquals("Fun", typeParameter.getName());
        assertEquals(2, typeParameter.getBound().size());
        assertEquals(Number.class.getName(), typeParameter.getBound().get(0));
        assertEquals(Runnable.class.getName(), typeParameter.getBound().get(1));
    }

    /**
     * testing integer annotation argument
     */
    @Test
    public void testClass16() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class16.class),
            getSourceFile(Annotation3.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("id", argument.getName());
        assertEquals("int", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("3", argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing integer array annotation argument
     */
    @Test
    public void testClass17() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class17.class),
            getSourceFile("Annotation5")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("int", argument.getType().getQualified());
        assertEquals(2, argument.getValue().size());
        assertEquals("1", argument.getValue().get(0));
        assertEquals("2", argument.getValue().get(1));
        assertTrue(argument.isPrimitive());
        assertTrue(argument.isArray());
    }

    /**
     * testing integer array annotation argument
     */
    @Test
    public void testClass18() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class18.class),
            getSourceFile("Annotation6")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("java.lang.String", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("hey", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing enum annotation argument
     */
    @Test
    public void testClass19() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class19.class),
            getSourceFile("Annotation7"),
            getSourceFile(Enum1.class)};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals(getSimpledataPackage() + ".Enum1", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("a", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing class annotation argument
     */
    @Test
    public void testClass20() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class20.class),
            getSourceFile("Annotation8")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("java.lang.Class", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("java.lang.String", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing character annotation argument
     */
    @Test
    public void testClass21() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class21.class),
            getSourceFile("Annotation10")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("char", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals(Integer.toString('a'), argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing 0 character annotation argument
     */
    @Test
    public void testClass22() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class22.class),
            getSourceFile("Annotation10")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("char", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals(Integer.toString(0), argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing boolean annotation argument
     */
    @Test
    public void testClass23() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class23.class),
            getSourceFile("Annotation11")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("boolean", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals(Boolean.TRUE.toString(), argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing empty int array annotation argument
     */
    @Test
    public void testClass24() {
        String[] sourceFiles = new String[] {
            getSourceFile(Class24.class),
            getSourceFile("Annotation5")};
        Root rootNode = executeJavadoc(null, null, null, sourceFiles, null, new String[] {"-dryrun"});

        Package packageNode = rootNode.getPackage().get(0);
        Class classNode = packageNode.getClazz().get(0);
        AnnotationInstance instance = classNode.getAnnotation().get(0);
        AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("int", argument.getType().getQualified());
        assertEquals(0, argument.getValue().size());
        assertTrue(argument.isPrimitive());
        assertTrue(argument.isArray());
    }
}
