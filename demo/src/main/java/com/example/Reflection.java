package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Reflection {
    private static HashMap<String, Object> objetosCriados = new HashMap<>();

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

    public static Method[] listaMetodos(Class<?> classe){
        Method[] methods = classe.getDeclaredMethods();
        for(int i=0; i<methods.length;i++){
            System.out.println("Nome do método:"+methods[i].getName());
            Parameter[] parametros = methods[i].getParameters();
            for(int x = 0; x<parametros.length; x++){
                System.out.print(parametros[x]+"-");
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
        return methods;
    }

    public static Object instanciarObjeto(Class<?> classe, Scanner scanner) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        System.out.println("Selecione qual construtor usar: ");
        Constructor[] construtores = classe.getDeclaredConstructors();
        Constructor constructor;
        Class<?>[] types;
        Object[] parametros;
       

        for(int i=0;i<construtores.length;i++){
            System.out.print(i+" - "+construtores[i].getParameterCount()+" parametros: ");
            types=construtores[i].getParameterTypes();
            for(int x=0;x<types.length;x++){
                System.out.print(types[x].getSimpleName()+" - ");
            }
            System.out.println("====");
        }
        int opcao=Integer.parseInt(scanner.nextLine());
        if(opcao<0||opcao>=construtores.length){
            throw new IllegalArgumentException("Opção invalida. ");
        }else{
            constructor=construtores[opcao];
            types=constructor.getParameterTypes();
            parametros = new Object[types.length];
            

            for(int i=0;i<parametros.length;i++){
                System.out.println("Digite o valor do parâmetro "+types[i].getSimpleName()+" <"+types[i].getSimpleName()+">");
                String input = scanner.nextLine();
                parametros[i]=converter(input, types[i]);
            }
        }

        
       return constructor.newInstance(parametros);

    }

    public static Object converter(String input, Class<?> type){
        if(type==String.class){
            return input;
        }else if(type==int.class || type==Integer.class){
            return Integer.parseInt(input);
        }else if(type==double.class || type==Double.class){
            return Double.parseDouble(input);
        }else if(type==long.class || type==Long.class){
            return Long.parseLong(input);
        }else if(type==float.class || type==Float.class){
            return Float.parseFloat(input);
        }else if(type==boolean.class || type==Boolean.class){
            if(!input.equalsIgnoreCase("true")&&!input.equalsIgnoreCase("false")){
                throw new IllegalArgumentException("valor invalido. Digite true ou false");
            }else{
                return Boolean.parseBoolean(input);
            }
        }else{
            throw new IllegalArgumentException("Conversão não implementada para o tipo: "+type.getName());
        }
    }

   


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        
        try {
            
            Class<?> classeAtual = escolheClasse(scanner);
            Object objetoAtual = instanciarObjeto(classeAtual,scanner);
            Method[] listaMetodos = listaMetodos(classeAtual);
            

            
            Method metodo=objetoAtual.getClass().getMethod("toString", null);
            System.out.println(metodo.invoke(objetoAtual, args));

            Method metodo2=objetoAtual.getClass().getMethod("getCodigo", null);
            objetosCriados.put((String) metodo2.invoke(objetoAtual, args), metodo2);
            
            System.out.println(classeAtual.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }

}
