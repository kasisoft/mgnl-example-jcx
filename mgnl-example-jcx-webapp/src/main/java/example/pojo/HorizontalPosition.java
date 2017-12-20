package example.pojo;

import com.kasisoft.libs.common.xml.adapters.*;
import com.vaadin.v7.data.util.converter.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum HorizontalPosition {

  Left   ( "left"   ),
  Center ( "center" ),
  Right  ( "right"  );
  
  String   value;
  
  HorizontalPosition( String val ) {
    value = val;
  }
  
  @Override
  public String toString() {
    return value;
  }
  
  @SuppressWarnings("deprecation")
  public static class HorizontalPositionAdapter extends XmlToTypeAdapter<String, HorizontalPosition> implements Converter<String, HorizontalPosition> {

    public HorizontalPositionAdapter() {
      super( new EnumerationAdapter<>( HorizontalPosition.class ) );
    }
    
    @Override
    public HorizontalPosition convertToModel( String value, Class<? extends HorizontalPosition> targetType, Locale locale ) throws Converter.ConversionException {
      try {
        return unmarshal( value );
      } catch( Exception ex ) {
        throw new Converter.ConversionException(ex);
      }
    }

    @Override
    public String convertToPresentation( HorizontalPosition value, Class<? extends String> targetType, Locale locale ) throws Converter.ConversionException {
      try {
        return marshal( value );
      } catch( Exception ex ) {
        throw new Converter.ConversionException(ex);
      }
    }

    @Override
    public Class<HorizontalPosition> getModelType() {
      return HorizontalPosition.class;
    }

    @Override
    public Class<String> getPresentationType() {
      return String.class;
    }

  } /* ENDCLASS */
  
} /* ENDENUM */
