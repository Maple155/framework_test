package annotation.annotation;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GenericAnnotationScanner {
    
    @SuppressWarnings("unchecked")
    public static <T extends Annotation> List<Class<?>> findClassesWithAnnotation(
            String packageName, Class<T> annotationClass) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            URL resource = classLoader.getResource(path);
            
            if (resource != null) {
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    scanDirectory(packageName, directory, classes, annotationClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
    
    private static <T extends Annotation> void scanDirectory(String packageName, File directory, 
                                    List<Class<?>> classes, Class<T> annotationClass) {
        File[] files = directory.listFiles();
        if (files == null) return;
        
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(packageName + "." + file.getName(), file, classes, annotationClass);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(annotationClass)) {
                        classes.add(clazz);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    // Ignorer les classes qui ne peuvent pas être chargées
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Utilisation avec votre annotation @Controller
        List<Class<?>> controllerClasses = findClassesWithAnnotation("com.test.controller", Controller.class);
        
        System.out.println("Classes annotées avec @Controller :");
        for (Class<?> clazz : controllerClasses) {
            System.out.println("- " + clazz.getName());
            
            // Optionnel : afficher d'autres informations sur l'annotation
            Controller controllerAnnotation = clazz.getAnnotation(Controller.class);
            System.out.println("  Annotation: " + controllerAnnotation);
        }
        
        if (controllerClasses.isEmpty()) {
            System.out.println("Aucune classe trouvée avec l'annotation @Controller");
        }
    }

//     # Compilation
// javac annotation/annotation/*.java
// javac -cp . annotation/annotation/*.java
// javac -cp . com/test/controller/*.java
// javac -cp . com/test/test/*.java

// # Exécution
// java -cp . annotation.annotation.GenericAnnotationScanner

}