package com.newpos.latte.compile;

import com.google.auto.service.AutoService;
import com.newpos.latte.annotation.AppRegisterGenerator;
import com.newpos.latte.annotation.EntryGenerator;
import com.newpos.latte.annotation.PayEntyGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;


/**
 * Created by Administrator on 2018/3/22 0022.
 */

@AutoService(Process.class)
public class LatterProcessor extends AbstractProcessor {



    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotations = new LinkedHashSet<>();
        for(Class<? extends Annotation> annotation : getSupportAnnotationType()){
            supportedAnnotations.add(annotation.getCanonicalName());
        }
        return supportedAnnotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generateEntyCode(roundEnv);
        generatePayEntyCode(roundEnv);
        generateAppRegisterCode(roundEnv);
        return true;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotationType(){
        Set<Class<? extends Annotation>> nanotations = new LinkedHashSet<>();
        nanotations.add(PayEntyGenerator.class);
        nanotations.add(PayEntyGenerator.class);
        nanotations.add(AppRegisterGenerator.class);
        return nanotations;
    }


    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {

        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntyCode(RoundEnvironment emv){
        EntryVisitor entryVisitor = new EntryVisitor(processingEnv.getFiler());
        scan(emv, EntryGenerator.class, entryVisitor);
    }
    private void generatePayEntyCode(RoundEnvironment emv){
        PayEntryVisitor entryVisitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(emv, PayEntyGenerator.class, entryVisitor);
    }
    private void generateAppRegisterCode(RoundEnvironment emv){
        AppRegisterVisitor entryVisitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(emv, AppRegisterGenerator.class, entryVisitor);
    }
}
