package parser.utils;

import java.lang.reflect.Method;

public class ReflectionUtils {
   /*
    * @param className class name of the object
    * @param methodName method name of the class
    * @param filePath file path of the pdf
    * @return the extracted text of the pdf using the given className and the methodName
    * */
    public static  String getTextFromPdf(String className, String methodName, String filePath) {
      String pdfText = "";
      try {
         Class<?> clazz = Class.forName(className);
         Object object = clazz.getDeclaredConstructor().newInstance();
         Method method = object.getClass().getDeclaredMethod(methodName, String.class);
         Object obj = method.invoke(object, filePath);
         if(obj instanceof String ){
            pdfText = (String) obj;
         }
      }catch (Exception e){
         e.printStackTrace();
      }
      return pdfText;
   }
}
