/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur;

import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.*;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Database;
import net.sf.jsqlparser.schema.Server;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.alter.AlterExpression;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import net.sf.jsqlparser.statement.create.view.AlterView;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.FunctionItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.Offset;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import org.jdom.Element;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class DocumentToSQL {


    /*
     * public CaseExpression visit(Element a) { if (a == null) { return null; }
     *
     * ElementList expressionList = new ElementList();
     * expressionList.setName("whenClauses"); if (a.getWhenClauses() != null) {
     * for (Expression elem : a.getWhenClauses()) {
     * expressionList.addElement(visit(elem)); } }
     *
     * return newElement(a.getClass().getSimpleName(), new
     * Element[]{visit(a.getSwitchExpression()), visit(expressionList),
     * visit(a.getElseExpression())}, null); }
     */
 /*
     * public Element visit(MySQLGroupConcat a) { if (a == null) { return null;
     * } ElementList orderByList = new ElementList();
     * orderByList.setName("orderBy"); if (a.getOrderByElements() != null) { for
     * (OrderByElement elem : a.getOrderByElements()) {
     * orderByList.addElement(visit(elem)); } } // TODO: a.getSeparator() }
     */
 /*
     * public Element visit(WithinGroupExpression a) { if (a == null) { return
     * null; } ElementList orderByList = new ElementList();
     * orderByList.setName("orderBy"); if (a.getOrderByElements() != null) { for
     * (OrderByElement elem : a.getOrderByElements()) {
     * orderByList.addElement(visit(elem)); } }
     *
     * }
     */
    public Object visit(BinaryExpression a, Element b) {
        List<Element> childs = b.getChildren();
        a.setLeftExpression((Expression) visit(childs.get(0)));
        a.setRightExpression((Expression) visit(childs.get(1)));
        return a;
    }

    /*
     * public Element visit(ExpressionList a) { if (a == null) { return null; }
     * Expression[] tmp = a.getExpressions().toArray(new Expression[0]);
     * Element[] tmpExp = new Element[tmp.length]; for (int i = 0; i <
     * tmp.length; i++) { tmpExp[i] = visit(tmp[i]); } }
     *
     */
    public Object visit(Element a) {
        String elemClass = a.getAttributeValue("class");
        String elemLabel = a.getAttributeValue("label");
        List<Element> childs = a.getChildren();

        switch (elemClass) {
            case "Addition":
                return visit(new Addition(), a);
            case "BitwiseAnd":
                return visit(new BitwiseAnd(), a);
            case "BitwiseOr":
                return visit(new BitwiseOr(), a);
            case "BitwiseXor":
                return visit(new BitwiseXor(), a);
            case "Concat":
                return visit(new Concat(), a);
            case "Division":
                return visit(new Division(), a);
            case "Modulo":
                return visit(new Modulo(), a);
            case "Multiplication":
                return visit(new Multiplication(), a);
            case "Subtraction":
                return visit(new Subtraction(), a);
            case "AndExpression":
                return new AndExpression((Expression) visit(childs.get(0)), (Expression) visit(childs.get(1)));
            case "OrExpression":
                return new OrExpression((Expression) visit(childs.get(0)), (Expression) visit(childs.get(1)));
            case "EqualsTo":
                return visit(new EqualsTo(), a);
            case "GreaterThan":
                return visit(new GreaterThan(), a);
            case "GreaterThanEquals":
                return visit(new GreaterThanEquals(), a);
            case "JsonOperator":
                // hier fehlt noch der Operator
                return visit(new JsonOperator(""), a);
            case "LikeExpression":
                return visit(new LikeExpression(), a);
            case "Matches":
                return visit(new Matches(), a);
            case "MinorThanEquals":
                return visit(new MinorThanEquals(), a);
            case "NotEqualsTo":
                return visit(new NotEqualsTo(), a);
            case "RegExpMatchOperator":
                // der Operatortype stimmt hier noch nicht
                return visit(new RegExpMatchOperator(null), a);
            case "Alias":
                return new Alias(elemLabel);
            case "AllComparisonExpression":
                SubSelect tmp2 = (SubSelect) visit(childs.get(0));
                AllComparisonExpression tmp = new AllComparisonExpression(tmp2);
                return tmp;
            case "AnyComparisonExpression":
                return new AnyComparisonExpression(AnyType.valueOf(elemLabel), (SubSelect) visit(childs.get(0)));
            case "CaseExpression":
                CaseExpression tmp22 = new CaseExpression();
                tmp22.setSwitchExpression((Expression) visit(childs.get(0)));
                tmp22.setElseExpression((Expression) visit(childs.get(2)));

                List<Expression> whenList = new ArrayList<Expression>();
                List<Element> aa = childs.get(1).getChildren();
                for (Element a2 : aa) {
                    whenList.add((Expression) visit(a2));
                }
                tmp22.setWhenClauses(whenList);
                return tmp22;
            case "CastExpression":
                CastExpression tmp23 = new CastExpression();
                tmp23.setLeftExpression((Expression) visit(childs.get(0)));
                tmp23.setType((ColDataType) visit(childs.get(1)));
                return tmp23;
            case "DateTimeLiteralExpression":
                DateTimeLiteralExpression tmp3 = new DateTimeLiteralExpression();
                tmp3.setValue(elemLabel);
                return tmp3;
            case "DateValue":
                return new DateValue(elemLabel);
            case "DoubleValue":
                return new DoubleValue(elemLabel);
            case "ExtractExpression":
                ExtractExpression tmp13 = new ExtractExpression();
                tmp13.setName(elemLabel);
                tmp13.setExpression((Expression) visit(childs.get(0)));
                return tmp13;
            case "Function":
                Function function = new Function();
                function.setParameters((ExpressionList) visit(childs.get(0)));
                function.setKeep((KeepExpression) visit(childs.get(1)));
                function.setName(elemLabel);
                return function;
            case "HexValue":
                return new HexValue(elemLabel);
            case "IntervalExpression":
                IntervalExpression tmp4 = new IntervalExpression();
                tmp4.setParameter(elemLabel);
                return tmp4;
            case "JdbcNamedParameter":
                JdbcNamedParameter tmp7 = new JdbcNamedParameter();
                tmp7.setName(elemLabel);
                return tmp7;
            case "JdbcParameter":
                JdbcParameter tmp14 = new JdbcParameter();
                tmp14.setIndex(Integer.parseInt(elemLabel));
                return tmp14;
            case "JsonExpression":
                JsonExpression tmp8 = new JsonExpression();
                tmp8.setColumn((Column) visit(childs.get(0)));
                return tmp8;
            case "KeepExpression":
                KeepExpression tmp27 = new KeepExpression();
                List<OrderByElement> tmp27OrderBy = new ArrayList<>();
                for (Element aa3 : childs) {
                    tmp27OrderBy.add((OrderByElement) visit(aa3));
                }
                tmp27.setOrderByElements(tmp27OrderBy);
                return tmp27;
            case "OrderByElement":
                OrderByElement tmp9 = new OrderByElement();
                tmp9.setExpression((Expression) visit(childs.get(0)));
                return tmp9;
            case "LongValue":
                return new LongValue(elemLabel);
            case "MySQLGroupConcat":
                MySQLGroupConcat tmp15 = new MySQLGroupConcat();
                tmp15.setExpressionList((ExpressionList) visit(childs.get(0)));
                List<OrderByElement> tmp15OrderBy = new ArrayList<>();
                List<Element> aa4 = childs.get(1).getChildren();
                for (Element aa3 : aa4) {
                    tmp15OrderBy.add((OrderByElement) visit(aa3));
                }
                tmp15.setOrderByElements(tmp15OrderBy);
                return tmp15;
            case "NotExpression":
                return new NotExpression((Expression) visit(childs.get(0)));
            case "NullValue":
                return new NullValue();
            case "NumericBind":
                NumericBind tmp5 = new NumericBind();
                tmp5.setBindId(Integer.parseInt(elemLabel));
                return tmp5;
            case "OracleHierarchicalExpression":
                OracleHierarchicalExpression tmp28 = new OracleHierarchicalExpression();
                tmp28.setStartExpression((Expression) visit(childs.get(0)));
                tmp28.setConnectExpression((Expression) visit(childs.get(1)));
                return tmp28;
            case "OracleHint":
                OracleHint tmp6 = new OracleHint();
                tmp6.setComment(elemLabel);
                return tmp6;
            case "Parenthesis":
                return new Parenthesis((Expression) visit(childs.get(0)));
            case "RowConstructor":
                RowConstructor tmp29 = new RowConstructor();
                tmp29.setExprList((ExpressionList) visit(childs.get(0)));
                tmp29.setName(elemLabel);
                return tmp29;
            case "SignedExpression":
                SignedExpression tmp30 = new SignedExpression(elemLabel.charAt(0), (Expression) visit(childs.get(0)));
                return tmp30;
            case "StringValue":
                return new StringValue(elemLabel);
            case "TimeKeyExpression":
                return new TimeKeyExpression(elemLabel);
            case "TimeValue":
                return new TimeValue(elemLabel);
            case "TimestampValue":
                return new TimestampValue(elemLabel);
            case "UserVariable":
                UserVariable tmp10 = new UserVariable();
                tmp10.setName(elemLabel);
                return tmp10;
            case "WhenClause":
                WhenClause tmp31 = new WhenClause();
                tmp31.setWhenExpression((Expression) visit(childs.get(0)));
                tmp31.setThenExpression((Expression) visit(childs.get(1)));
                return tmp31;
            case "WithinGroupExpression":
                //return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList()), visit(orderByList)}, a.getName());
                return null;
            case "Between":
                Between tmp32 = new Between();
                tmp32.setLeftExpression((Expression) visit(childs.get(0)));
                tmp32.setBetweenExpressionStart((Expression) visit(childs.get(1)));
                tmp32.setBetweenExpressionEnd((Expression) visit(childs.get(2)));
                return tmp32;
            case "ExistsExpression":
                //return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getRightExpression())}, null);
                return null;
            case "ExpressionList":
                //return newElement(a.getClass().getSimpleName(), tmpExp, null);
                return null;
            case "InExpression":
                // return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getLeftItemsList()), visit(a.getRightItemsList())}, null);
                return null;
            case "IsNullExpression":
                IsNullExpression tmp12 = new IsNullExpression();
                tmp12.setLeftExpression((IsNullExpression) visit(childs.get(0)));
                return tmp12;
            case "MultiExpressionList":
                MultiExpressionList tmp33 = new MultiExpressionList();
                for (Element aa6 : childs) {
                    tmp33.addExpressionList((ExpressionList) visit(aa6));
                }
                return tmp33;
            case "Column":
                return new Column((Table) visit(childs.get(0)), elemLabel);
            case "Database":
                return new Database(elemLabel);
            case "Server":
                return new Server(elemLabel);
            case "Table":
                Table tmp11 = new Table(elemLabel);
                tmp11.setDatabase((Database) visit(childs.get(0)));
                tmp11.setAlias((Alias) visit(childs.get(1)));
                return tmp11;
            case "SetStatement":
                // hier fehlt noch der Name!!!
                return new SetStatement(null, (Expression) visit(childs.get(0)));
            case "Alter":
                // TODO TODO TODO
                return new Alter();
            case "AlterExpression":
                // TODO TODO TODO
                AlterExpression tmp20 = new AlterExpression();
                tmp20.setColumnName(elemLabel);
                return tmp20;
            case "CreateIndex":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "ColDataType":
                // return newElement(a.getClass().getSimpleName(), new Element[]{visit(expressionList), characterSet}, a.getDataType());
                ColDataType tmp35 = new ColDataType();
                return null;
            case "ColumnDefinition":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "CreateTable":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "ElementList":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "Index":
                return null;
            case "AlterView":
                // TODO TODO TODO
                return new AlterView();
            case "CreateView":
                // TODO TODO TODO
                return new CreateView();
            case "Delete":
                // TODO TODO TODO
                return new Delete();
            case "Drop":
                // TODO TODO TODO
                return new Drop();
            case "Execute":
                Execute tmp21 = new Execute();
                tmp21.setName(elemLabel);
                tmp21.setExprList((ExpressionList) visit(childs.get(0)));
                return tmp21;
            case "Insert":
                // TODO TODO TODO
                return new Insert();
            case "Merge":
                // TODO TODO TODO
                return new Merge();
            case "Replace":
                // TODO TODO TODO
                return new Replace();
            case "AllColumns":
                // TODO TODO TODO
                return new AllColumns();
            case "AllTableColumns":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "FunctionItem":
                FunctionItem tmp16 = new FunctionItem();
                tmp16.setFunction((Function) visit(childs.get(0)));
                tmp16.setAlias((Alias) visit(childs.get(1)));
                return tmp16;
            case "Join":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "LateralSubSelect":
                // FEHLT NOCH FEHLT NOCH FEHLT NOCH FEHLT NOCH
                return null;
            case "PlainSelect":
                PlainSelect tmp39 = new PlainSelect();

                List<SelectItem> tmp39SelectItems = new ArrayList<>();
                List<Element> aa5 = childs.get(0).getChildren();
                for (Element aa6 : aa5) {tmp39SelectItems.add((SelectItem) visit(aa6));}
                tmp39.setSelectItems(tmp39SelectItems);
                
                List<Element> tmp40 = childs.get(1).getChildren();
                tmp39.setFromItem((FromItem) tmp40.get(0));
                
                // entfernt das erste Element
                tmp40.remove(0);
                List<Join> tmp41 = new ArrayList<>();
                for (Element aa7 : tmp40) {tmp41.add((Join) visit(aa7));}
                tmp39.setJoins(tmp41);
                
                tmp39.setHaving((Expression) visit(childs.get(2)));
                tmp39.setLimit((Limit) visit(childs.get(3)));
                tmp39.setOffset((Offset) visit(childs.get(4)));
                return tmp39;
            case "Limit":
                // TODO TODO TODO
                return new Limit();
            case "Offset":
                Offset tmp17 = new Offset();
                tmp17.setOffset(Long.parseUnsignedLong(elemLabel));
                return tmp17;
            case "Select":
                Select tmp42 = new Select();
                tmp42.setSelectBody((SelectBody) visit(childs.get(0)));
                List<Element> tmp43 = childs.get(1).getChildren();
                List<WithItem> tmp44 = new ArrayList<>();
                for (Element aa8 : tmp43) {tmp44.add((WithItem) visit(aa8));}
                tmp42.setWithItemsList(tmp44);
                return tmp42;
            case "SelectExpressionItem":
                SelectExpressionItem tmp45 = new SelectExpressionItem();
                tmp45.setExpression((Expression) visit(childs.get(0)));
                tmp45.setAlias((Alias) visit(childs.get(1)));
                return tmp45;
            case "SetOperationList":
                // TODO TODO TODO
                return new SetOperationList();
            case "SubJoin":
                // return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeft()), visit(a.getJoin()), visit(a.getAlias())}, null);
                return null;
            case "SubSelect":
                // return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSelectBody()), visit(a.getAlias()), visit(withItemsList)}, null);
                return new SubSelect();
            case "TableFunction":
                TableFunction tmp19 = new TableFunction();
                tmp19.setFunction((Function) visit(childs.get(0)));
                tmp19.setAlias((Alias) visit(childs.get(1)));
                return tmp19;
            case "ValuesList":
                // TODO TODO TODO
                return new ValuesList();
            case "WithItem":
                // TODO TODO TODO
                return new WithItem();
            case "Truncate":
                Truncate tmp18 = new Truncate();
                tmp18.setTable((Table) visit(childs.get(0)));
                return tmp18;
            case "Update":
                // TODO TODO TODO
                return new Update();
        }
        return null;
    }

}
