package org.gds.parser.structure;
import org.apache.log4j.Logger;
import org.gds.parser.ValueType;
import java.util.ArrayList;
import java.util.List;

/**
 * 字段结构树
 * @author GCC
 */
public class FieldTree implements Cloneable{

    private static final Logger logger = Logger.getLogger(FieldTree.class);
    private int fatherid;
    //标记 {}/[]
    private String item;

    private int id;

    private String fieldname;

    private Object value;

    private ValueType valuetype;

    private List<FieldTree> childs = new ArrayList<>();

    private FieldTree(){}

    public FieldTree(String fieldname){
        this.fieldname = fieldname;
    }

    public FieldTree(int id,String fieldname){
        this.id = id;
        this.fieldname = fieldname;
    }

    public FieldTree(int id,int fatherid,String fieldname){
        this.id = id;
        this.fatherid = fatherid;
        this.fieldname = fieldname;
    }

    public FieldTree(int id,String fieldname,List<FieldTree> childs){
        this.id = id;
        this.fieldname = fieldname;
        this.childs = childs;
    }

    public FieldTree(int id,int fatherid,String fieldname,List<FieldTree> childs){
        this.id = id;
        this.fatherid = fatherid;
        this.fieldname = fieldname;
        this.childs = childs;
    }

    public FieldTree(int id,String fieldname,FieldTree child){
        this.id = id;
        this.fieldname = fieldname;
        child.setId(this.id+1);
        child.setFatherid(this.id);
        this.childs.add(child);
    }

    public void addChild(FieldTree child){
        child.setId(this.id+1);
        child.setFatherid(this.id);
        this.childs.add(child);
    }

    public int getFatherid() {
        return fatherid;
    }

    public void setFatherid(int fatherid) {
        this.fatherid = fatherid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public ValueType getValuetype() {
        return valuetype;
    }

    public void setValuetype(ValueType valuetype) {
        this.valuetype = valuetype;
    }

    public List<FieldTree> getChilds() {
        return childs;
    }

    public void setChilds(List<FieldTree> childs) {
        this.childs = childs;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public FieldTree clone(){
        try {
            FieldTree fieldTree = (FieldTree) super.clone();
            fieldTree.setChilds(new ArrayList<>());
            return fieldTree;
        }catch (Exception e){
            logger.error("clone is error. there will create a new Object."+e.getMessage());
        }
        return new FieldTree();
    }
}
