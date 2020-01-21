package br.com.uol.cotacoes.hibernate;

import java.util.Arrays;
import java.util.Collection;

import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.datatype.IDbProductRelatable;

/**
 * Type especifico para HSQLDB. 
 * Ele foi criado para limpar o log da aplicação dos warnings
 * 
 * @author mzp_dferraz
 *
 */
public class HSQLDBDataTypeFactory extends DefaultDataTypeFactory {
	private static final Collection DATABASE_PRODUCTS = Arrays.asList(new String[]{"hsql database engine", "derby"});
    /**
     * @see IDbProductRelatable#getValidDbProducts()
     */
    public Collection getValidDbProducts()
    {
      return DATABASE_PRODUCTS;
    }
}
