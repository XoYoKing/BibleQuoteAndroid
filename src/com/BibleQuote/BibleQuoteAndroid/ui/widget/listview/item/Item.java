package com.BibleQuote.BibleQuoteAndroid.ui.widget.listview.item;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.BibleQuote.BibleQuoteAndroid.ui.widget.listview.itemview.ItemView;
import com.BibleQuote.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract class Item {

	private SparseArray<Object> mTags;
	private Object mTag;

	/**
	 * Set to true when this item is enabled
	 */
	public boolean enabled;

	/**
	 * Create a new item.
	 */
	public Item() {
		// By default, an item is enabled
		enabled = true;
	}

	/**
	 * Return the tag associated to that item.
	 *
	 * @return The tag associated to this item.
	 */
	public Object getTag() {
		return mTag;
	}

	/**
	 * Set the tag associated with this item. A tag is often used to store extra
	 * information.
	 *
	 * @param tag The tag associated to this item
	 */
	public void setTag(Object tag) {
		mTag = tag;
	}

	/**
	 * Return the tag associated with this item and the specified key.
	 *
	 * @param key The key of the tag to retrieve
	 * @return The tag associated to the key <em>key</em> or null if no tags are
	 *         associated to that key
	 */
	public Object getTag(int key) {
		return (mTags == null) ? null : mTags.get(key);
	}

	/**
	 * Set a tag associated with this item and a key. A tag is often used to
	 * store extra information.
	 *
	 * @param key The key for the specified tag
	 * @param tag A tag that will be associated to that item
	 */
	public void setTag(int key, Object tag) {
		if (mTags == null) {
			mTags = new SparseArray<Object>();
		}
		mTags.put(key, tag);
	}

	/**
	 * Return a view that is associated to the current item. The returned view
	 * is normally capable of being a good recipient for all item's information.
	 *
	 * @param context The Context in which the {@link com.BibleQuote.BibleQuoteAndroid.ui.widget.listview.itemview.ItemView} will be used
	 * @param parent  The parent View of that new View. The parent is usually the
	 *                parent ListView and may be used to retrieve the correct
	 *                LayoutParams type.
	 * @return A new allocated view for the current Item
	 */
	public abstract ItemView newView(Context context, ViewGroup parent);

	/**
	 * Helper method to inflate a layout using a given Context and a layoutID.
	 *
	 * @param context  The current context
	 * @param layoutID The identifier of the layout to inflate
	 * @return A newly inflated view
	 */
	protected static ItemView createCellFromXml(Context context, int layoutID, ViewGroup parent) {
		return (ItemView) LayoutInflater.from(context).inflate(layoutID, parent, false);
	}

	/**
	 * Inflate this Item from an XML resource.
	 *
	 * @param r
	 * @param parser
	 * @param attrs
	 * @throws org.xmlpull.v1.XmlPullParserException
	 * @throws java.io.IOException
	 */
	public void inflate(Resources r, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
		TypedArray a = r.obtainAttributes(attrs, R.styleable.Item);
		enabled = a.getBoolean(R.styleable.Item_enabled, enabled);
		a.recycle();
	}

}
