package example.internal;

import com.kasisoft.mgnl.util.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
public interface JcrProps {

  JcrProperty<String>  Title = new JcrProperty<>( "title", PropertyLoaders::toString  , PropertySavers::saveString  , "" );

} /* ENDINTERFACE */
