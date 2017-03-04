/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.utils.structures;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.*;
import net.sf.jsqlparser.statement.*;
import net.sf.jsqlparser.statement.alter.*;
import net.sf.jsqlparser.statement.create.index.*;
import net.sf.jsqlparser.statement.create.table.*;
import net.sf.jsqlparser.statement.create.view.*;
import net.sf.jsqlparser.statement.delete.*;
import net.sf.jsqlparser.statement.drop.*;
import net.sf.jsqlparser.statement.execute.*;
import net.sf.jsqlparser.statement.insert.*;
import net.sf.jsqlparser.statement.merge.*;
import net.sf.jsqlparser.statement.replace.*;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.truncate.*;
import net.sf.jsqlparser.statement.update.*;
import org.jdom.Element;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class JSQLToDocument {

    private Element newElement(String name, Element[] children, String label) {
        Element newElement = new Element("node");
        newElement.setAttribute("class", name);
        if (label != null) {
            newElement.setAttribute("label", label);
        }

        if (children != null) {
            for (Element q3 : children) {
                if (q3 == null) {
                    Element newElement2 = new Element("node");
                    newElement.addContent(newElement2);
                } else {
                    newElement.addContent(q3);
                }
            }
        }
        return newElement;
    }

    public Element visit(ItemsList a) {
        if (a == null) {
            return null;
        }

        if (a instanceof ExpressionList) {
            return visit((ExpressionList) a);
        }
        if (a instanceof MultiExpressionList) {
            return visit((MultiExpressionList) a);
        }
        if (a instanceof SubSelect) {
            return visit((SubSelect) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(Statement a) {
        if (a == null) {
            return null;
        }
        if (a instanceof SetStatement) {
            return visit((SetStatement) a);
        }
        if (a instanceof Alter) {
            return visit((Alter) a);
        }
        if (a instanceof CreateIndex) {
            return visit((CreateIndex) a);
        }
        if (a instanceof CreateTable) {
            return visit((CreateTable) a);
        }
        if (a instanceof AlterView) {
            return visit((AlterView) a);
        }
        if (a instanceof CreateView) {
            return visit((CreateView) a);
        }
        if (a instanceof Delete) {
            return visit((Delete) a);
        }
        if (a instanceof Drop) {
            return visit((Drop) a);
        }
        if (a instanceof Execute) {
            return visit((Execute) a);
        }
        if (a instanceof Insert) {
            return visit((Insert) a);
        }
        if (a instanceof Merge) {
            return visit((Merge) a);
        }
        if (a instanceof Replace) {
            return visit((Replace) a);
        }
        if (a instanceof Select) {
            return visit((Select) a);
        }
        if (a instanceof Truncate) {
            return visit((Truncate) a);
        }
        if (a instanceof Update) {
            return visit((Update) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(SelectItem a) {
        if (a == null) {
            return null;
        }
        if (a instanceof AllColumns) {
            return visit((AllColumns) a);
        }
        if (a instanceof SelectExpressionItem) {
            return visit((SelectExpressionItem) a);
        }
        if (a instanceof AllTableColumns) {
            return visit((AllTableColumns) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(SelectBody a) {
        if (a == null) {
            return null;
        }
        if (a instanceof PlainSelect) {
            return visit((PlainSelect) a);
        }
        if (a instanceof SetOperationList) {
            return visit((SetOperationList) a);
        }
        if (a instanceof WithItem) {
            return visit((WithItem) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(FromItem a) {
        if (a == null) {
            return null;
        }
        if (a instanceof Table) {
            return visit((Table) a);
        }
        if (a instanceof LateralSubSelect) {
            return visit((LateralSubSelect) a);
        }
        if (a instanceof SubJoin) {
            return visit((SubJoin) a);
        }
        if (a instanceof SubSelect) {
            return visit((SubSelect) a);
        }
        if (a instanceof TableFunction) {
            return visit((TableFunction) a);
        }
        if (a instanceof ValuesList) {
            return visit((ValuesList) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(Expression a) {
        if (a == null) {
            return null;
        }
        if (a instanceof BinaryExpression) {
            return visit((BinaryExpression) a);
        }
        if (a instanceof AllComparisonExpression) {
            return visit((AllComparisonExpression) a);
        }
        if (a instanceof AnalyticExpression) {
            return visit((AnalyticExpression) a);
        }
        if (a instanceof AnyComparisonExpression) {
            return visit((AnyComparisonExpression) a);
        }
        if (a instanceof CaseExpression) {
            return visit((CaseExpression) a);
        }
        if (a instanceof CastExpression) {
            return visit((CastExpression) a);
        }
        if (a instanceof DateTimeLiteralExpression) {
            return visit((DateTimeLiteralExpression) a);
        }
        if (a instanceof DateValue) {
            return visit((DateValue) a);
        }
        if (a instanceof DoubleValue) {
            return visit((DoubleValue) a);
        }
        if (a instanceof ExtractExpression) {
            return visit((ExtractExpression) a);
        }
        if (a instanceof Function) {
            return visit((Function) a);
        }
        if (a instanceof HexValue) {
            return visit((HexValue) a);
        }
        if (a instanceof IntervalExpression) {
            return visit((IntervalExpression) a);
        }
        if (a instanceof JdbcNamedParameter) {
            return visit((JdbcNamedParameter) a);
        }
        if (a instanceof JdbcParameter) {
            return visit((JdbcParameter) a);
        }
        if (a instanceof JsonExpression) {
            return visit((JsonExpression) a);
        }
        if (a instanceof KeepExpression) {
            return visit((KeepExpression) a);
        }
        if (a instanceof LongValue) {
            return visit((LongValue) a);
        }
        if (a instanceof MySQLGroupConcat) {
            return visit((MySQLGroupConcat) a);
        }
        if (a instanceof NotExpression) {
            return visit((NotExpression) a);
        }
        if (a instanceof NullValue) {
            return visit((NullValue) a);
        }
        if (a instanceof NumericBind) {
            return visit((NumericBind) a);
        }
        if (a instanceof OracleHierarchicalExpression) {
            return visit((OracleHierarchicalExpression) a);
        }
        if (a instanceof OracleHint) {
            return visit((OracleHint) a);
        }
        if (a instanceof Parenthesis) {
            return visit((Parenthesis) a);
        }
        if (a instanceof RowConstructor) {
            return visit((RowConstructor) a);
        }
        if (a instanceof SignedExpression) {
            return visit((SignedExpression) a);
        }
        if (a instanceof StringValue) {
            return visit((StringValue) a);
        }
        if (a instanceof TimeKeyExpression) {
            return visit((TimeKeyExpression) a);
        }
        if (a instanceof TimeValue) {
            return visit((TimeValue) a);
        }
        if (a instanceof TimestampValue) {
            return visit((TimestampValue) a);
        }
        if (a instanceof UserVariable) {
            return visit((UserVariable) a);
        }
        if (a instanceof WhenClause) {
            return visit((WhenClause) a);
        }
        if (a instanceof WithinGroupExpression) {
            return visit((WithinGroupExpression) a);
        }
        if (a instanceof Between) {
            return visit((Between) a);
        }
        if (a instanceof ExistsExpression) {
            return visit((ExistsExpression) a);
        }
        if (a instanceof IsNullExpression) {
            return visit((IsNullExpression) a);
        }
        if (a instanceof Column) {
            return visit((Column) a);
        }

        throw new RuntimeException("TODO:" + a.getClass().getSimpleName());
    }

    public Element visit(BinaryExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getRightExpression())}, null);
    }

    public Element visit(Alias a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    public Element visit(AllComparisonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect())}, null);
    }

    public Element visit(AnyComparisonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect())}, a.getAnyType().name());
    }

    public Element visit(CaseExpression a) {
        if (a == null) {
            return null;
        }

        ElementList expressionList = new ElementList();
        expressionList.setName("whenClauses");
        if (a.getWhenClauses() != null) {
            for (Expression elem : a.getWhenClauses()) {
                expressionList.addElement(visit(elem));
            }
        }

        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSwitchExpression()), visit(expressionList), visit(a.getElseExpression())}, null);
    }

    public Element visit(CastExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getType())}, null);
    }

    public Element visit(DateTimeLiteralExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    public Element visit(DateValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    public Element visit(DoubleValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    public Element visit(ExtractExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, a.getName());
    }

    public Element visit(Function a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getParameters()), visit(a.getKeep())}, a.getName());
    }

    public Element visit(HexValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    public Element visit(IntervalExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getParameter());
    }

    public Element visit(JdbcNamedParameter a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    public Element visit(JdbcParameter a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getIndex().toString());
    }

    public Element visit(JsonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getColumn())}, null);
    }

    public Element visit(KeepExpression a) {
        if (a == null) {
            return null;
        }

        OrderByElement[] tmp = a.getOrderByElements().toArray(new OrderByElement[0]);
        Element[] tmpExp = new Element[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            tmpExp[i] = visit(tmp[i]);
        }
        return newElement(a.getClass().getSimpleName(), tmpExp, a.getName());
    }

    public Element visit(OrderByElement a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getNullOrdering()
        // TODO: a.isAsc()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    public Element visit(LongValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    public Element visit(MySQLGroupConcat a) {
        if (a == null) {
            return null;
        }
        ElementList orderByList = new ElementList();
        orderByList.setName("orderBy");
        if (a.getOrderByElements() != null) {
            for (OrderByElement elem : a.getOrderByElements()) {
                orderByList.addElement(visit(elem));
            }
        }
        // TODO: a.getSeparator()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpressionList()), visit(orderByList)}, null);
    }

    public Element visit(NotExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    public Element visit(NullValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(NumericBind a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, Integer.toString(a.getBindId()));
    }

    public Element visit(OracleHierarchicalExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getStartExpression()), visit(a.getConnectExpression())}, null);
    }

    public Element visit(OracleHint a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    public Element visit(Parenthesis a) {
        if (a == null) {
            return null;
        }
        // TODO a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    public Element visit(RowConstructor a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList())}, a.getName());
    }

    public Element visit(SignedExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, Character.toString(a.getSign()));
    }

    public Element visit(StringValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    public Element visit(TimeKeyExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getStringValue());
    }

    public Element visit(TimeValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    public Element visit(TimestampValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    public Element visit(UserVariable a) {
        if (a == null) {
            return null;
        }
        // TODO: a.isDoubleAdd()
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    public Element visit(WhenClause a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getWhenExpression()), visit(a.getThenExpression())}, null);
    }

    public Element visit(WithinGroupExpression a) {
        if (a == null) {
            return null;
        }
        ElementList orderByList = new ElementList();
        orderByList.setName("orderBy");
        if (a.getOrderByElements() != null) {
            for (OrderByElement elem : a.getOrderByElements()) {
                orderByList.addElement(visit(elem));
            }
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList()), visit(orderByList)}, a.getName());
    }

    public Element visit(Between a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getBetweenExpressionStart()), visit(a.getBetweenExpressionEnd())}, null);
    }

    public Element visit(ExistsExpression a) {
        if (a == null) {
            return null;
        }
        // TODO: a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getRightExpression())}, null);
    }

    public Element visit(ExpressionList a) {
        if (a == null) {
            return null;
        }
        Expression[] tmp = a.getExpressions().toArray(new Expression[0]);
        Element[] tmpExp = new Element[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            tmpExp[i] = visit(tmp[i]);
        }
        return newElement(a.getClass().getSimpleName(), tmpExp, null);
    }

    public Element visit(InExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getLeftItemsList()), visit(a.getRightItemsList())}, null);
    }

    public Element visit(IsNullExpression a) {
        if (a == null) {
            return null;
        }
        // TODO; a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression())}, null);
    }

    public Element visit(MultiExpressionList a) {
        if (a == null) {
            return null;
        }
        ExpressionList[] tmp = a.getExprList().toArray(new ExpressionList[0]);
        Element[] tmpExp = new Element[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            tmpExp[i] = visit(tmp[i]);
        }
        return newElement(a.getClass().getSimpleName(), tmpExp, null);
    }

    public Element visit(Column a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, a.getColumnName());
    }

    public Element visit(Database a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getServer()
        return newElement(a.getClass().getSimpleName(), null, a.getDatabaseName());
    }

    public Element visit(Server a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getFullyQualifiedName());
    }

    public Element visit(Table a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getDatabase()), visit(a.getAlias())}, a.getName());
    }

    public Element visit(SetStatement a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    public Element visit(Alter a) {
        if (a == null) {
            return null;
        }
        ElementList expressionList = new ElementList();
        expressionList.setName("alterExpressions");
        if (a.getAlterExpressions() != null) {
            for (AlterExpression elem : a.getAlterExpressions()) {
                expressionList.addElement(visit(elem));
            }
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable()), visit(expressionList)}, null);
    }

    public Element visit(AlterExpression a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, a.getColumnName());
    }

    public Element visit(CreateIndex a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable()), visit(a.getIndex())}, null);
    }

    public Element visit(ColDataType a) {
        if (a == null) {
            return null;
        }
        Element characterSet = newElement("characterSet", null, a.getCharacterSet());

        ElementList expressionList = new ElementList();
        expressionList.setName("arguments");
        if (a.getArgumentsStringList() != null) {
            for (String elem : a.getArgumentsStringList()) {
                Element newElem = newElement("text", null, elem);
                expressionList.addElement(newElem);
            }
        }

        // TODO: a.getArrayData
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(expressionList), characterSet}, a.getDataType());
    }

    public Element visit(ColumnDefinition a) {
        if (a == null) {
            return null;
        }

        ElementList columnSpecsList = new ElementList();
        columnSpecsList.setName("columnSpecs");
        if (a.getColumnSpecStrings() != null) {
            for (String elem : a.getColumnSpecStrings()) {
                Element newElem = newElement("text", null, elem);
                columnSpecsList.addElement(newElem);
            }
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getColDataType()), visit(columnSpecsList)}, a.getColumnName());
    }

    public Element visit(CreateTable a) {
        if (a == null) {
            return null;
        }
        ElementList columnDefinitionsList = new ElementList();
        columnDefinitionsList.setName("columns");
        if (a.getColumnDefinitions() != null) {
            for (ColumnDefinition col : a.getColumnDefinitions()) {
                columnDefinitionsList.addElement(visit(col));
            }
        }

        ElementList indexesList = new ElementList();
        indexesList.setName("indexes");
        if (a.getIndexes() != null) {
            for (Index index : a.getIndexes()) {
                indexesList.addElement(visit(index));
            }
        }

        ElementList createOptionsList = new ElementList();
        createOptionsList.setName("createOptions");
        if (a.getCreateOptionsStrings() != null) {
            for (String elem : a.getCreateOptionsStrings()) {
                Element newElem = newElement("text", null, elem);
                createOptionsList.addElement(newElem);
            }
        }

        ElementList tableOptionsList = new ElementList();
        tableOptionsList.setName("tableOptions");
        if (a.getTableOptionsStrings() != null) {
            for (String elem : a.getTableOptionsStrings()) {
                Element newElem = newElement("text", null, elem);
                tableOptionsList.addElement(newElem);
            }
        }

        Element ifNotExists = newElement("text", null, Boolean.toString(a.isIfNotExists()));
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable()), visit(columnDefinitionsList), visit(indexesList), visit(a.getSelect()), visit(createOptionsList), visit(tableOptionsList), ifNotExists}, null);
    }

    public Element visit(ElementList a) {
        if (a == null) {
            return null;
        }
        if (a.getName() == null) {
            return newElement(a.getClass().getSimpleName(), a.getElements().toArray(new Element[0]), null);
        }
        return newElement(a.getName(), a.getElements().toArray(new Element[0]), null);
    }

    public Element visit(Index a) {
        if (a == null) {
            return null;
        }

        ElementList columnNamesList = new ElementList();
        columnNamesList.setName("columnNames");
        if (a.getColumnsNames() != null) {
            for (String elem : a.getColumnsNames()) {
                Element newElem = newElement("text", null, elem);
                columnNamesList.addElement(newElem);
            }
        }

        ElementList indexSpecsList = new ElementList();
        indexSpecsList.setName("indexSpecs");
        if (a.getIndexSpec() != null) {
            for (String elem : a.getIndexSpec()) {
                Element newElem = newElement("text", null, elem);
                indexSpecsList.addElement(newElem);
            }
        }

        Element type = newElement("text", null, a.getType());
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(columnNamesList), visit(indexSpecsList), type}, a.getName());
    }

    public Element visit(AlterView a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(CreateView a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Delete a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Drop a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Execute a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList())}, a.getName());
    }

    public Element visit(Insert a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Merge a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Replace a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(AllColumns a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(AllTableColumns a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, null);
    }

    public Element visit(FunctionItem a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getFunction()), visit(a.getAlias())}, null);
    }

    public Element visit(Join a) {
        if (a == null) {
            return null;
        }
        //  TODO: a.getUsingColumns()
        // TODO: der Typ des Join fehlt nocht
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getRightItem()), visit(a.getOnExpression())}, null);
    }

    public Element visit(LateralSubSelect a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect()), visit(a.getAlias())}, null);
    }

    public Element visit(PlainSelect a) {
        if (a == null) {
            return null;
        }
        ElementList selectList = new ElementList();
        selectList.setName("selectList");
        if (a.getSelectItems() != null) {
            for (SelectItem item : a.getSelectItems()) {
                selectList.addElement(visit(item));
            }
        }

        ElementList fromList = new ElementList();
        fromList.setName("fromList");
        fromList.addElement(visit(a.getFromItem()));

        if (a.getJoins() != null) {
            for (Join tmp : a.getJoins()) {
                fromList.addElement(visit(tmp));
            }
        }

        return newElement(a.getClass().getSimpleName(), new Element[]{visit(selectList), visit(fromList), visit(a.getWhere()), visit(a.getHaving()), visit(a.getLimit()), visit(a.getOffset())}, null);
    }

    public Element visit(Limit a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(Offset a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getOffsetParam()
        return newElement(a.getClass().getSimpleName(), null, String.valueOf(a.getOffset()));
    }

    public Element visit(Select a) {
        if (a == null) {
            return null;
        }
        ElementList withItemsList = new ElementList();
        withItemsList.setName("withItems");
        if (a.getWithItemsList() != null) {
            for (WithItem elem : a.getWithItemsList()) {
                withItemsList.addElement(visit(elem));
            }
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSelectBody()), visit(withItemsList)}, null);
    }

    public Element visit(SelectExpressionItem a) {
        if (a == null) {
            return null;
        }
        Element q = newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression()), visit(a.getAlias())}, null);
        return q;
    }

    public Element visit(SetOperationList a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    public Element visit(SubJoin a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeft()), visit(a.getJoin()), visit(a.getAlias())}, null);
    }

    public Element visit(SubSelect a) {
        if (a == null) {
            return null;
        }

        ElementList withItemsList = new ElementList();
        withItemsList.setName("withItems");
        if (a.getWithItemsList() != null) {
            for (WithItem elem : a.getWithItemsList()) {
                withItemsList.addElement(visit(elem));
            }
        }

        // TODO: a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSelectBody()), visit(a.getAlias()), visit(withItemsList)}, null);
    }

    public Element visit(TableFunction a) {
        if (a == null) {
            return null;
        }

        // TODO a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getFunction()), visit(a.getAlias())}, null);
    }

    public Element visit(ValuesList a) {
        if (a == null) {
            return null;
        }
        ElementList columnNamesList = new ElementList();
        columnNamesList.setName("columnNames");
        for (String elem : a.getColumnNames()) {
            columnNamesList.addElement(new Element("text", null, elem));
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getMultiExpressionList()), visit(columnNamesList), visit(a.getAlias())}, null);
    }

    public Element visit(WithItem a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    public Element visit(Truncate a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, null);
    }

    public Element visit(Update a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

}
