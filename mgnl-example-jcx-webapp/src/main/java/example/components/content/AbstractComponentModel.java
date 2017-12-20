package example.components.content;

import info.magnolia.rendering.template.configured.*;

import info.magnolia.rendering.model.*;

import com.kasisoft.mgnl.jcx.*;

import javax.jcr.*;
import javax.xml.bind.annotation.*;

import java.util.function.*;

import lombok.experimental.*;

import lombok.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractComponentModel<D extends ConfiguredTemplateDefinition> extends RenderingModelImpl<D> {

  @XmlTransient
  JcxUnmarshaller                                                          unmarshaller;
  
  @XmlTransient
  BiFunction<Node, AbstractComponentModel<D>, AbstractComponentModel<D>>   loader;
  
  public AbstractComponentModel( Node content, D definition, RenderingModel<?> parent, JcxUnmarshaller jcxUnmarshaller ) {
    super( content, definition, parent );
    unmarshaller  = jcxUnmarshaller;
    loader        = (BiFunction<Node, AbstractComponentModel<D>, AbstractComponentModel<D>>) unmarshaller.createLoader( getClass() );
  }

  @Override
  public String execute() {
    loader.apply( getNode(), this );
    return super.execute();
  }
  
} /* ENDCLASS */
