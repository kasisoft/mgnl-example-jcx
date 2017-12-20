package example.pojo;

import static example.internal.JcrProps.*;

import info.magnolia.repository.*;

import info.magnolia.templating.functions.*;

import com.kasisoft.mgnl.jcx.*;

import javax.inject.*;
import javax.jcr.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

import lombok.extern.slf4j.*;

import lombok.experimental.*;

import lombok.*;

import info.magnolia.context.*;

/**
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Getter @Setter @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkModel implements IPostProcessor, IContent {

  @XmlAttribute
  String                link;
  
  @XmlAttribute
  String                title;
  
  @XmlAttribute
  @XmlJavaTypeAdapter(HorizontalPosition.HorizontalPositionAdapter.class)
  HorizontalPosition    position;
  
  @XmlTransient 
  String                target;
  
  @XmlTransient
  TemplatingFunctions   cmsFn;
  
  @Inject
  public LinkModel( TemplatingFunctions templatingFunctions ) {
    cmsFn = templatingFunctions;
  }

  @Override
  public void postprocess() {
    if( position == null ) {
      position = HorizontalPosition.Left;
    }
    if( link != null ) {
      target = cmsFn.link( RepositoryConstants.WEBSITE, link );
    }
    if( (title == null) && (link != null) ) {
      try {
        // for this example we're only assuming internal links
        Node node = MgnlContext.getJCRSession( RepositoryConstants.WEBSITE ).getNodeByIdentifier( link );
        title     = Title.getValue( node );
      } catch( Exception ex ) {
        title     = "Whatever";
        log.error( ex.getLocalizedMessage(), ex );
      }
    }
    if( title == null ) {
      title = "Whatever";
    }
  }
  
  @Override
  public boolean hasContent() {
    // without a link we don't consider this element to have content
    return target != null;
  }
  
} /* ENDCLASS */