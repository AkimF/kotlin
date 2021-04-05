// !WITH_NEW_INFERENCE
// FILE: a.kt
package boundsWithSubstitutors
    open class A<T>
    class B<X : A<X>>()

    class C : A<C>()

    val a = B<C>()
    val a1 = <!INAPPLICABLE_CANDIDATE!>B<!><Int>()

    class X<A, B : A>()

    val b = X<Any, X<A<C>, C>>()
    val b0 = <!INAPPLICABLE_CANDIDATE!>X<!><Any, Any?>()
    val b1 = X<Any, <!UPPER_BOUND_VIOLATED!>X<A<C>, String><!>>()

// FILE: b.kt
  open class A {}
  open class B<T : A>()

  class Pair<A, B>

  abstract class C<T : B<Int>, X :  (B<Char>) -> Pair<B<Any>, B<A>>>() : <!INAPPLICABLE_CANDIDATE!>B<Any><!>() { // 2 errors
    val a = <!INAPPLICABLE_CANDIDATE!>B<!><Char>() // error

    abstract val x :  (B<Char>) -> B<Any>
  }


fun test() {
    <!INAPPLICABLE_CANDIDATE!>foo<!><Int?>()
    foo<Int>()
    bar<Int?>()
    bar<Int>()
    <!INAPPLICABLE_CANDIDATE!>bar<!><Double?>()
    <!INAPPLICABLE_CANDIDATE!>bar<!><Double>()
    1.<!INAPPLICABLE_CANDIDATE!>buzz<!><Double>()
}

fun <T : Any> foo() {}
fun <T : Int?> bar() {}
fun <T : <!FINAL_UPPER_BOUND!>Int<!>> Int.buzz() : Unit {}
