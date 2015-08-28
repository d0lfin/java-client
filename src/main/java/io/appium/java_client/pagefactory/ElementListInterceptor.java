package io.appium.java_client.pagefactory;

import io.appium.java_client.MobileElement;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *
 * Intercepts requests to the list of {@link MobileElement}
 *
 */
class ElementListInterceptor implements MethodInterceptor{

	private final ElementLocator locator;
	private final String elementName;

	ElementListInterceptor(ElementLocator locator, String name) {
		this.locator = locator;
		this.elementName = name;
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
        if(Object.class.getDeclaredMethod("finalize").equals(method)){
            return proxy.invokeSuper(obj, args);  //invokes .finalize of the proxy-object
        }
		if(Object.class.getDeclaredMethod("toString").equals(method)){
			return elementName;
		}

		ArrayList<WebElement> realElements = new ArrayList<WebElement>();
		realElements.addAll(locator.findElements());
		return method.invoke(realElements, args);
	}

}
