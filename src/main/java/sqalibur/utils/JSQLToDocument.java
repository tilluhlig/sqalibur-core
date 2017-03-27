/*
 * Copyright (C) 2017 Till Uhlig <till.uhlig@student.uni-halle.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sqalibur.utils;

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
import sqalibur.utils.structures.ElementList;

/**
 * Diese Klasse wandelt JSQL nach Document um. Dazu kann die visit() Methode
 * aufrufen und ihr ein Objekt des JSQL-Parsers übergeben, diese wird die
 * weiteren Aufrufe rekursiv durchführen.
 *
 * @author Till Uhlig {@literal <till.uhlig@student.uni-halle.de>}
 */
public class JSQLToDocument {

    /*
     * erzeugt ein neues Element
     */
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

    /**
     * wandelt ein ItemsList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein Statement-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein SelectItem-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein SelectBody-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein FromItem-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein Expression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein BinaryExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(BinaryExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getRightExpression())}, null);
    }

    /**
     * wandelt ein Alias-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Alias a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    /**
     * wandelt ein AllComparisonExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AllComparisonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect())}, null);
    }

    /**
     * wandelt ein AnyComparisonExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AnyComparisonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect())}, a.getAnyType().name());
    }

    /**
     * wandelt ein CaseExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein CastExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(CastExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getType())}, null);
    }

    /**
     * wandelt ein DateTimeLiteralExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(DateTimeLiteralExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    /**
     * wandelt ein DateValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(DateValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    /**
     * wandelt ein DoubleValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(DoubleValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    /**
     * wandelt ein ExtractExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(ExtractExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, a.getName());
    }

    /**
     * wandelt ein Function-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Function a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getParameters()), visit(a.getKeep())}, a.getName());
    }

    /**
     * wandelt ein HexValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(HexValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    /**
     * wandelt ein IntervalExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(IntervalExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getParameter());
    }

    /**
     * wandelt ein JdbcNamedParameter-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(JdbcNamedParameter a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    /**
     * wandelt ein JdbcParameter-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(JdbcParameter a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getIndex().toString());
    }

    /**
     * wandelt ein JsonExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(JsonExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getColumn())}, null);
    }

    /**
     * wandelt ein KeepExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein OrderByElement-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(OrderByElement a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getNullOrdering()
        // TODO: a.isAsc()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    /**
     * wandelt ein LongValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(LongValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.toString());
    }

    /**
     * wandelt ein MySQLGroupConcat-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein NotExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(NotExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    /**
     * wandelt ein NullValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(NullValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein NumericBind-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(NumericBind a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, Integer.toString(a.getBindId()));
    }

    /**
     * wandelt ein OracleHierarchicalExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(OracleHierarchicalExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getStartExpression()), visit(a.getConnectExpression())}, null);
    }

    /**
     * wandelt ein OracleHint-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(OracleHint a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    /**
     * wandelt ein Parenthesis-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Parenthesis a) {
        if (a == null) {
            return null;
        }
        // TODO a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    /**
     * wandelt ein RowConstructor-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(RowConstructor a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList())}, a.getName());
    }

    /**
     * wandelt ein SignedExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(SignedExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, Character.toString(a.getSign()));
    }

    /**
     * wandelt ein StringValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(StringValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue());
    }

    /**
     * wandelt ein TimeKeyExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(TimeKeyExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getStringValue());
    }

    /**
     * wandelt ein TimeValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(TimeValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    /**
     * wandelt ein TimestampValue-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(TimestampValue a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getValue().toString());
    }

    /**
     * wandelt ein UserVariable-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(UserVariable a) {
        if (a == null) {
            return null;
        }
        // TODO: a.isDoubleAdd()
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    /**
     * wandelt ein WhenClause-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(WhenClause a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getWhenExpression()), visit(a.getThenExpression())}, null);
    }

    /**
     * wandelt ein WithinGroupExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein Between-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Between a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getBetweenExpressionStart()), visit(a.getBetweenExpressionEnd())}, null);
    }

    /**
     * wandelt ein ExistsExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(ExistsExpression a) {
        if (a == null) {
            return null;
        }
        // TODO: a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getRightExpression())}, null);
    }

    /**
     * wandelt ein ExpressionList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein InExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(InExpression a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression()), visit(a.getLeftItemsList()), visit(a.getRightItemsList())}, null);
    }

    /**
     * wandelt ein IsNullExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(IsNullExpression a) {
        if (a == null) {
            return null;
        }
        // TODO; a.isNot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeftExpression())}, null);
    }

    /**
     * wandelt ein MultiExpressionList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein Column-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Column a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, a.getColumnName());
    }

    /**
     * wandelt ein Database-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Database a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getServer()
        return newElement(a.getClass().getSimpleName(), null, a.getDatabaseName());
    }

    /**
     * wandelt ein Server-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Server a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, a.getFullyQualifiedName());
    }

    /**
     * wandelt ein Table-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Table a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot()
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getDatabase()), visit(a.getAlias())}, a.getName());
    }

    /**
     * wandelt ein SetStatement-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(SetStatement a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression())}, null);
    }

    /**
     * wandelt ein Alter-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein AlterExpression-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AlterExpression a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, a.getColumnName());
    }

    /**
     * wandelt ein CreateIndex-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(CreateIndex a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable()), visit(a.getIndex())}, null);
    }

    /**
     * wandelt ein ColDataType-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein ColumnDefinition-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein CreateTable-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein ElementList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(ElementList a) {
        if (a == null) {
            return null;
        }
        if (a.getName() == null) {
            return newElement(a.getClass().getSimpleName(), a.getElements().toArray(new Element[0]), null);
        }
        return newElement(a.getName(), a.getElements().toArray(new Element[0]), null);
    }

    /**
     * wandelt ein Index-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein AlterView-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AlterView a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein CreateView-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(CreateView a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Delete-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Delete a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Drop-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Drop a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Execute-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Execute a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExprList())}, a.getName());
    }

    /**
     * wandelt ein Insert-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Insert a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Merge-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Merge a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Replace-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Replace a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein AllColumns-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AllColumns a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein AllTableColumns-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(AllTableColumns a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, null);
    }

    /**
     * wandelt ein FunctionItem-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(FunctionItem a) {
        if (a == null) {
            return null;
        }
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getFunction()), visit(a.getAlias())}, null);
    }

    /**
     * wandelt ein Join-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Join a) {
        if (a == null) {
            return null;
        }
        //  TODO: a.getUsingColumns()
        // TODO: der Typ des Join fehlt nocht
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getRightItem()), visit(a.getOnExpression())}, null);
    }

    /**
     * wandelt ein LateralSubSelect-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(LateralSubSelect a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getSubSelect()), visit(a.getAlias())}, null);
    }

    /**
     * wandelt ein PlainSelect-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

        return  newElement(a.getClass().getSimpleName(), new Element[]{visit(selectList), visit(fromList), visit(a.getWhere()), visit(a.getHaving()), visit(a.getLimit()), visit(a.getOffset())}, null);
    }

    /**
     * wandelt ein Limit-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Limit a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein Offset-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Offset a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getOffsetParam()
        return newElement(a.getClass().getSimpleName(), null, String.valueOf(a.getOffset()));
    }

    /**
     * wandelt ein Select-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein SelectExpressionItem-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(SelectExpressionItem a) {
        if (a == null) {
            return null;
        }
        Element q = newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getExpression()), visit(a.getAlias())}, null);
        return q;
    }

    /**
     * wandelt ein SetOperationList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(SetOperationList a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

    /**
     * wandelt ein SubJoin-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(SubJoin a) {
        if (a == null) {
            return null;
        }
        // TODO: a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getLeft()), visit(a.getJoin()), visit(a.getAlias())}, null);
    }

    /**
     * wandelt ein SubSelect-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein TableFunction-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(TableFunction a) {
        if (a == null) {
            return null;
        }

        // TODO a.getPivot
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getFunction()), visit(a.getAlias())}, null);
    }

    /**
     * wandelt ein ValuesList-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
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

    /**
     * wandelt ein WithItem-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(WithItem a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, a.getName());
    }

    /**
     * wandelt ein Truncate-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Truncate a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), new Element[]{visit(a.getTable())}, null);
    }

    /**
     * wandelt ein Update-Objekt in ein Element um
     *
     * @param a das Eingabeobjekt
     * @return die Eingabe als Element
     */
    public Element visit(Update a) {
        if (a == null) {
            return null;
        }
        // TODO TODO TODO
        return newElement(a.getClass().getSimpleName(), null, null);
    }

}
