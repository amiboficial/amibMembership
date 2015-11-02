package mx.amib.sistemas.membership.service.exception;

/***
 * 
 * Esta excepción se lanza si se intenta hacer "save" o "update" empleando un
 * nombre de usuario (sin importar mayusculas ni minusculas) ya existente en la base de datos
 * 
 * @author Gabriel
 *
 */
public class UsernameNonUniqueException extends Exception{
	
}
