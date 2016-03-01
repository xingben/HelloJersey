/**
 * 
 */
package net.xingws.sample.webservice.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.ws.rs.NameBinding;


/**
 * @author benxing
 *
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Compress {

}
