open class C<T : C<T>>
class TestOK : C<TestOK>()
class TestFail : <!INAPPLICABLE_CANDIDATE!>C<C<TestFail>><!>()
