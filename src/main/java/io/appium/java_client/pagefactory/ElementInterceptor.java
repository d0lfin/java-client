package io.appium.java_client.pagefactory;

import io.appium.java_client.MobileElement;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Intercepts requests to {@link MobileElement}
 *
 */
class ElementInterceptor implements MethodInterceptor {
    private final ElementLocator locator;
	private final String elementName;

	private WebElement element;
	
	ElementInterceptor(ElementLocator locator, String name) {
		this.locator = locator;
		this.elementName = name;
	}
	
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
        if(Object.class.getDeclaredMethod("finalize").equals(method)){
            return proxy.invokeSuper(obj, args);  //invokes .finalize of the proxy-object
        }
        if("toString".equals(method.getName())){
            return elementName;
        }
		try {
			return method.invoke(element, args);
		} catch (Exception e) {
			element = locator.findElement();
			return method.invoke(element, args);
		}
	}

}
