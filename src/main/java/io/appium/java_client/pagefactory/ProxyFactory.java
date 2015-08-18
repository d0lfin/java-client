package io.appium.java_client.pagefactory;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Original class is a super class of a 
 * proxy object here
 */
abstract class ProxyFactory {

	private ProxyFactory() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getEnhancedProxy(Class<T> requiredClazz, MethodInterceptor interceptor){
		return (T) Enhancer.create(requiredClazz, interceptor);
	}

}
