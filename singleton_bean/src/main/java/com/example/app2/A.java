//package com.example.app2;
//
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Lookup;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//
//@Service
////By default Singleton scope
//public class A {
//
//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Autowired
//    private ObjectFactory<B> bObjectFactory;
//
//    @Autowired
//    private B b;
//
//    public String access(){
//      return b.getBData();       // 40  40
//     // return applicationContext.getBean(B.class).getBData() ; // 25   11
//     // return bObjectFactory .getObject().getBData(); // 36   41
//     //  return getBBean().getBData();   // 30 10
//    }
//    @Lookup
//    public B getBBean(){
//        return null;
//    }
//
//}
