package com.comet.eai.converters;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
/**
 * Converts a java.util.Map to XML, specifying an 'entry'
 * element with 'key' and 'value' children.
 * <p>Note: 'key' and 'value' is not the name of the generated tag. The
 * children are serialized as normal elements and the implementation expects
 * them in the order 'key'/'value'.</p>
 * <p>Supports java.util.HashMap, java.util.Hashtable,
 * java.util.LinkedHashMap and java.util.concurrent.ConcurrentHashMap.</p>
 *
 * @author <a href="mailto:hzxia@qq.com">Jacky Xia</a>
 */
public class MapConverter extends AbstractCollectionConverter {
    public MapConverter(Mapper mapper) {
        super(mapper);
    }

    public boolean canConvert(Class type) {
        return type.equals(HashMap.class)
                || type.equals(Hashtable.class)
                || type.getName().equals("java.util.LinkedHashMap")
                || type.getName().equals("java.util.concurrent.ConcurrentHashMap")
                || type.getName().equals("sun.font.AttributeMap") // Used by java.awt.Font in JDK 6
                ;
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map map = (Map) source;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
//            ExtendedHierarchicalStreamWriterHelper.startNode(writer, mapper().serializedClass(Map.Entry.class), entry.getClass());

//            writeItem(entry.getKey(), context, writer);
//            writeItem(entry.getValue(), context, writer);
            writeItem(entry, context, writer);

//            writer.endNode();
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map map = (Map) createCollection(context.getRequiredType());
        populateMap(reader, context, map);
        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {
        populateMap(reader, context, map, map);
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map, Map target) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            putCurrentEntryIntoMap(reader, context, map, target);
            reader.moveUp();
        }
    }

    protected void putCurrentEntryIntoMap(HierarchicalStreamReader reader, UnmarshallingContext context,
                                          Map map, Map target) {
//        reader.moveDown();
        String key = reader.getNodeName();
        Object obj = readItem(reader, context, map);

//        reader.moveUp();

//        reader.moveDown();
//        Object value = readItem(reader, context, map);
//        reader.moveUp();

        target.put(key, obj);
    }

    /**
     * Write item data
     *
     * @param item
     * @param context
     * @param writer
     */
    protected void writeItem(Object item, MarshallingContext context, HierarchicalStreamWriter writer) {
        if (item == null) {
            String name = mapper().serializedClass(null);
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, name, Mapper.Null.class);
            writer.endNode();
        } else {
            Map.Entry entry = (Map.Entry)item;
            String name = (String)entry.getKey();
            writer.startNode(name);

            if(entry.getValue() != null) {
                context.convertAnother(entry.getValue());
            } else {
                context.convertAnother("");
            }

            writer.endNode();
        }
    }

    /**
     * read item data
     *
     * @param reader
     * @param context
     * @param current
     * @return
     */
    protected Object readItem(HierarchicalStreamReader reader, UnmarshallingContext context, Object current) {
        Class type = null;

        try{
            type = HierarchicalStreams.readClassType(reader, mapper());
        } catch (Exception e) {
            type = String.class;
        }

        return context.convertAnother(current, type);
    }
}
