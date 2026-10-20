package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Reflection {
    private static HashMap<String, Object> objeto = new HashMap<>();

    public static Class<?> escolheClasse(Scanner scanner) throws ClassNotFoundException{
       TiposProdutos [] tipos = TiposProdutos.values();
       System.out.println("Selecione o tipo de classe: ");
       for(int i=0;i<tipos.length;i++){
            System.out.println(i+" - "+tipos[i]);
       }
       int i=Integer.parseInt(scanner.nextLine());
       Class<?> classe = Class.forName(tipos[i].getCaminhoCompleto());
       return classe;
    }

    public static void listaMetodos(Class<?> classe){
        Method[] methods = classe.getDeclaredMethods();
        for(int i=0; i<methods.length;i++){
            System.out.println("Nome do método:"+methods[i].getName());
            Parameter[] parametros = methods[i].getParameters();
            for(int x = 0; x<parametros.length; x++){
                System.out.println(parametros[x]);
            }
        }
        System.out.println("=====");
        Constructor[] construtores = classe.getDeclaredConstructors();
        for(int i=0;i<construtores.length;i++){
            System.out.println("Construtor: "+construtores[i].getName());
            System.out.println("qtd parametros: "+construtores[i].getParameterCount());
            Class<?>[] types = construtores[i].getParameterTypes();
            for(int x=0; x<types.length;x++){
                System.out.println(types[x].getSimpleName());
                
            }
        }
    }

    public static Object[] listarConstrutores(Class<?> classe, Scanner scanner) throws NoSuchMethodException{
        System.out.println("Selecione qual construtor usar: ");
        Constructor[] construtores = classe.getDeclaredConstructors();
        for(int i=0;i<construtores.length;i++){
            System.out.println(i+" - "+construtores[i].getParameterCount()+" parametros: ");
            Class<?>[] types = construtores[i].getParameterTypes();
            for(int x=0;x<types.length;x++){
                System.out.print(types[x].getSimpleName()+" - ");
            }
        }
        int opcao=Integer.parseInt(scanner.nextLine());
        Object[] params = construtores[opcao].getGenericParameterTypes();
        for(int i=0;i<params.length;i++){
            System.out.println(params[i].toString());
            Class xClass = params[i].getClass();
            System.out.println(xClass.e);
        }
        return params;

    }



   
    public static <O>O instanciarContrutor(Class<O> clase, Object ... parametros) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Class<?> [] tipo = new Class<?>[parametros.length];
        for(int i=0;i<parametros.length; i++){
            tipo[i]=parametros[i].getClass();
        }
        Constructor<O> construtor = clase.getConstructor(tipo);
        return construtor.newInstance(parametros);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        
        try {
            
            Class<?> classeAtual = escolheClasse(scanner);
            Object [] params = listarConstrutores(classeAtual,scanner);
            //Object objeto=instanciarContrutor(classeAtual, params);  

            objeto.toString();
            
            System.out.println(classeAtual.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }

}
