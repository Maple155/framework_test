package annotation.annotation;

import java.lang.reflect.Method;

public class Exemple {

    @GetURL(url = "/GetURL")
    public void url() {
    }

    public static void main(String[] args) {
        // Class<Exemple> clazz = Exemple.class;

        // for (Method method : clazz.getDeclaredMethods()) {
        //     if (method.isAnnotationPresent(GetURL.class)) {
        //         GetURL annotation = method.getAnnotation(GetURL.class);

        //         System.out.println("Méthode : " + method.getName());
        //         System.out.println("URL trouvée : " + annotation.url());
        //     }
        // }

        
    }
}
