(* Exercice 1 *)

(* 1 *)
(* Est Factorielle N x N -> Prop :
 * 1. On a is_fact(0, 1)
 * 2. Pour tout n, f on is_fact(n, f) -> is_fact(S(n), n * S(n))
 *)
 
Inductive is_fact : nat -> nat -> Prop :=
| is_fact_0 : is_fact 0 1
| is_fact_S : forall n f : nat, is_fact n f -> is_fact (S n) (f * (S n)). 
Print is_fact_ind.

(* 2 *)
Fixpoint fact (n : nat) : nat := match n with
| 0 => 1
| (S n) => (fact n) * (S n)
end.

(* 3 *)
Require Import FunInd.

Functional Scheme fact_ind := Induction for fact Sort Prop.
Print fact_ind.

(* 4 *)
Lemma fact_sound_ind : forall (n f : nat), (fact n) = f -> is_fact n f.
Proof.
	induction n.
	intros.
	rewrite <- H.
	simpl.
	apply is_fact_0.
	intros.
	rewrite <- H.
	simpl.
	apply is_fact_S.
	apply IHn.
	reflexivity.
Qed.

(* 5 *)
Lemma fact_sound_struct : forall (n f : nat), (fact n) = f -> is_fact n f.
Proof.
	intro.
	functional induction (fact n) using fact_ind.
	intros.
	rewrite <- H.
	apply is_fact_0.
	intros.
	rewrite <- H.
	apply is_fact_S.
	apply IHn0.
	reflexivity.
Qed.