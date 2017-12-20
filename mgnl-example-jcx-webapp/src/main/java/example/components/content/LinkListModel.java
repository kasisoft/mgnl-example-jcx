package example.components.content;

import info.magnolia.rendering.template.configured.*;

import info.magnolia.rendering.model.*;

import com.kasisoft.libs.common.annotation.*;
import com.kasisoft.mgnl.jcx.*;

import javax.jcr.*;
import javax.xml.bind.annotation.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

import example.pojo.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkListModel<D extends ConfiguredTemplateDefinition> extends AbstractComponentModel<D> {

  @XmlAttribute
  String            title;
  
  @XmlElement
  @GenericsType(LinkModel.class)
  List<LinkModel>   links;
  
  public LinkListModel( Node content, D definition, RenderingModel<?> parent, JcxUnmarshaller jcxUnmarshaller ) {
    super( content, definition, parent, jcxUnmarshaller );
  }
  
} /* ENDCLASS */
