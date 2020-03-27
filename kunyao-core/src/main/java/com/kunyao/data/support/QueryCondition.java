package com.kunyao.data.support;

import com.kunyao.data.IQueryCondition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryCondition implements IQueryCondition {
    private static final long serialVersionUID = 1L;
    public static final int LIKE = 1;
    public static final int IN = 2;
    public static final int NOTIN = 3;
    public static final int BETWEEN = 4;
    public static final int EQ = 5;
    public static final int NOTEQ = 6;
    public static final int GT = 7;
    public static final int GE = 8;
    public static final int LT = 9;
    public static final int LE = 10;
    public static final int ISNULL = 11;
    public static final int ISNOTNULL = 12;
    public static final int ISEMPTY = 13;
    public static final int ISNOTEMPTY = 14;
    public static final int AND = 201;
    public static final int OR = 202;
    private List<Rule> ruleList = new ArrayList<Rule>();
    private List<Order> orderList = new ArrayList<Order>();
    private String propertyName;

    private int pageNo;
    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    private QueryCondition() {}

    private QueryCondition(String propertyName) {
        this.propertyName = propertyName;
    }

    public static QueryCondition getInstance() {
        return new QueryCondition();
    }

    /**
     * 添加升序规则
     * @param propertyName
     * @return
     */
    @Override
    public QueryCondition addAscOrder(String propertyName) {
        this.orderList.add(Order.asc(propertyName));
        return this;
    }

    /**
     * 添加降序规则
     * @param propertyName
     * @return
     */
    @Override
    public QueryCondition addDescOrder(String propertyName) {
        this.orderList.add(Order.desc(propertyName));
        return this;
    }

    @Override
    public QueryCondition andIsNull(String propertyName) {
        this.ruleList.add(new Rule(ISNULL, propertyName).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andIsNotNull(String propertyName) {
        this.ruleList.add(new Rule(ISNOTNULL, propertyName).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andIsEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISEMPTY, propertyName).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andIsNotEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISNOTEMPTY, propertyName).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andLike(String propertyName, Object value) {
        this.ruleList.add(new Rule(LIKE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(EQ, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andBetween(String propertyName, Object... values) {
        this.ruleList.add(new Rule(BETWEEN, propertyName, values).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andIn(String propertyName, List<Object> values) {
        this.ruleList.add(new Rule(IN, propertyName, new Object[] { values }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(IN, propertyName, values).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andNotIn(String propertyName, List<Object> values) {
        this.ruleList.add(new Rule(NOTIN, propertyName, new Object[] { values }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition orNotIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(NOTIN, propertyName, values).setAndOr(OR));
        return this;
    }


    @Override
    public QueryCondition andNotEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(NOTEQ, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andGreaterThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(GT, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andGreaterEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(GE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andLessThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(LT, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }

    @Override
    public QueryCondition andLessEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(LE, propertyName, new Object[] { value }).setAndOr(AND));
        return this;
    }


    @Override
    public QueryCondition orIsNull(String propertyName) {
        this.ruleList.add(new Rule(ISNULL, propertyName).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orIsNotNull(String propertyName) {
        this.ruleList.add(new Rule(ISNOTNULL, propertyName).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orIsEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISEMPTY, propertyName).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orIsNotEmpty(String propertyName) {
        this.ruleList.add(new Rule(ISNOTEMPTY, propertyName).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orLike(String propertyName, Object value) {
        this.ruleList.add(new Rule(LIKE, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(EQ, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orBetween(String propertyName, Object... values) {
        this.ruleList.add(new Rule(BETWEEN, propertyName, values).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orIn(String propertyName, List<Object> values) {
        this.ruleList.add(new Rule(IN, propertyName, new Object[] { values }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orIn(String propertyName, Object... values) {
        this.ruleList.add(new Rule(IN, propertyName, values).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orNotEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(NOTEQ, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orGreaterThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(GT, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orGreaterEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(GE, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orLessThan(String propertyName, Object value) {
        this.ruleList.add(new Rule(LT, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public QueryCondition orLessEqual(String propertyName, Object value) {
        this.ruleList.add(new Rule(LE, propertyName, new Object[] { value }).setAndOr(OR));
        return this;
    }

    @Override
    public IQueryCondition paging(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        return this;
    }


    public List<Rule> getRuleList() {
        return this.ruleList;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    protected class Rule implements Serializable {
        private static final long serialVersionUID = 1L;
        private int type;	//规则的类型
        private String property_name;
        private Object[] values;
        private int andOr = AND;

        public Rule(int paramInt, String paramString) {
            this.property_name = paramString;
            this.type = paramInt;
        }

        public Rule(int paramInt, String paramString,
                    Object[] paramArrayOfObject) {
            this.property_name = paramString;
            this.values = paramArrayOfObject;
            this.type = paramInt;
        }

        public Rule setAndOr(int andOr){
            this.andOr = andOr;
            return this;
        }

        public int getAndOr(){
            return this.andOr;
        }

        public Object[] getValues() {
            return this.values;
        }

        public int getType() {
            return this.type;
        }

        public String getPropertyName() {
            return this.property_name;
        }
    }
    protected static class Order implements Serializable {
        private static final long serialVersionUID = 1L;
        private boolean ascending; //升序还是降序
        private String propertyName; //哪个字段升序，哪个字段降序

        @Override
        public String toString() {
            return propertyName + ' ' + (ascending ? "asc" : "desc");
        }

        /**
         * Constructor for Order.
         */
        protected Order(String propertyName, boolean ascending) {
            this.propertyName = propertyName;
            this.ascending = ascending;
        }

        /**
         * Ascending order
         *
         * @param propertyName
         * @return Order
         */
        public static Order asc(String propertyName) {
            return new Order(propertyName, true);
        }

        /**
         * Descending order
         *
         * @param propertyName
         * @return Order
         */
        public static Order desc(String propertyName) {
            return new Order(propertyName, false);
        }

    }
}
