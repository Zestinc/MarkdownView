package br.tiagohm.markdownview.ext.button.internal;

import android.text.TextUtils;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.CustomNodeRenderer;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.options.DataHolder;

import java.util.HashSet;
import java.util.Set;

import br.tiagohm.markdownview.ext.button.Button;

public class ButtonNodeRenderer implements NodeRenderer
{

  public ButtonNodeRenderer(DataHolder options)
  {
  }

  @Override
  public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers()
  {
    HashSet<NodeRenderingHandler<?>> set = new HashSet<>();
    set.add(new NodeRenderingHandler<>(Button.class, new CustomNodeRenderer<Button>()
    {
      @Override
      public void render(Button node, NodeRendererContext context, HtmlWriter html)
      {
        ButtonNodeRenderer.this.render(node, context, html);
      }
    }));
    return set;
  }

  private void render(final Button node, final NodeRendererContext context, final HtmlWriter html)
  {
    final String id = node.getUrl().toString();

    if(!TextUtils.isEmpty(id))
    {
      html.attr("id", id);
      html.attr("onclick", "javascript:android.onTap('button', '" + id + "');");
      html.srcPos(node.getChars()).withAttr().tag("button");

      if(node.hasChildren())
      {
        for(Node child : node.getChildren())
        {
          context.render(child);
        }
      }
      else
      {
        html.append(node.getText().toString().trim());
      }

      html.tag("/button");
    }
    else
    {
      context.renderChildren(node);
    }
  }

  public static class Factory implements NodeRendererFactory
  {
    @Override
    public NodeRenderer create(final DataHolder options)
    {
      return new ButtonNodeRenderer(options);
    }
  }
}