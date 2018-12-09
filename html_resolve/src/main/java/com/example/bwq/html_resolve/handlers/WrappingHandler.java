package com.example.bwq.html_resolve.handlers;

import android.text.SpannableStringBuilder;


import com.example.bwq.html_resolve.HtmlSpanner;
import com.example.bwq.html_resolve.SpanStack;
import com.example.bwq.html_resolve.TagNodeHandler;

import org.htmlcleaner.TagNode;

/**
 * TagNodeHandler which wraps another handler.
 * <p>
 * Default implementation just delegates to the wrapped handler.
 */
public class WrappingHandler extends TagNodeHandler {

    private TagNodeHandler wrappedHandler;

    public WrappingHandler(TagNodeHandler wrappedHandler) {
        this.wrappedHandler = wrappedHandler;
    }

    @Override
    public void handleTagNode(TagNode node, SpannableStringBuilder builder, int start, int end, SpanStack spanStack) {
        wrappedHandler.handleTagNode(node, builder, start, end, spanStack);
    }

    @Override
    public void setSpanner(HtmlSpanner spanner) {
        super.setSpanner(spanner);
        wrappedHandler.setSpanner(spanner);
    }

    protected TagNodeHandler getWrappedHandler() {
        return wrappedHandler;
    }
}
