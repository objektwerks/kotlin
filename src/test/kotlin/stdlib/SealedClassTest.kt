package stdlib

import org.junit.Test

class SealedClassTest {
    sealed class Expr
    class Const(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
    object NotANumber : Expr()

    private fun eval(expr: Expr): Int =
        when (expr) {
            is Const -> expr.value
            is Sum -> eval(expr.right) + eval(expr.left)
            NotANumber -> Double.NaN.toInt()
        }

    @Test fun sealedClass() {
        assert( eval( Sum( Const(1), Const(2) ) ) == 3)
    }
}