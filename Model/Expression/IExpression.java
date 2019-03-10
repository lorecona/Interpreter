package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.MyHeap;

public interface IExpression {
    int evaluate(IDictionary<String, Integer> st, MyHeap<Integer> heap);
    String toString();
}
