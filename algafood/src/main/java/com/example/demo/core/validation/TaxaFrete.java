package com.example.demo.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;

//copiado do positiveOrZero do model
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { }) //para ser uma constraint
@PositiveOrZero // validação para TaxaFreter
public @interface TaxaFrete {

	//copiado do positiveOrZero do model
	@OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
	String message() default "{TaxaFrete.invalida}";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	// -------------------------------
	
}
