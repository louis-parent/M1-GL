Parameters A B C : Prop.

Lemma p1 : A -> B -> A.
Proof.
	intro.
	intro.
	assumption.
Qed.

Print p1.

Lemma p2 : (A -> B -> C) -> (A -> B) -> A -> C.
Proof.
	intros.
	apply H.
	assumption.
	apply H0.
	assumption.
Qed.

Print p2.

Lemma p3 : A /\ B -> B.
Proof.
	intro.
	elim H.
	clear H.
	intros.
	assumption.
Qed.

Print p3.

Lemma p4 : B -> A \/ B.
Proof.
	intro.
	right.
	assumption.
Qed.

Print p4.

Lemma p5 : (A \/ B) -> (A -> C) -> (B -> C) -> C.
Proof.
	intros.
	elim H.
	assumption.
	assumption.
Qed.

Print p5.

Lemma p6 : A -> False -> ~A.
Proof.
	intros.
	intro.
	assumption.
Qed.

Print p6.

Lemma p7 : False -> A.
Proof.
	intro.
	elimtype False.
	assumption.
Qed.

Print p7.

Lemma p8 : (A <-> B) -> A -> B.
Proof.
	intros.
	elim H.
	intros.
	apply H1.
	assumption.
Qed.

Print p8.

Lemma p9 : (A <-> B) -> B -> A.
Proof.
	intros.
	elim H.
	intros.
	apply H2.
	assumption.
Qed.

Print p9.

Lemma p10 : (A -> B) -> (B -> A) -> (A <-> B).
Proof.
	intros.
	split.
	assumption.
	assumption.
Qed.

Print p10.


Parameter E : Set.
Parameter x y : E.
Parameter P : E -> Prop.
Parameter Q : E -> Prop.

Lemma f1: forall x : E, (P x -> (exists y : E, (P y \/ Q y))).
Proof.
	intros.
	exists x0.
	left.
	assumption.
Qed.

Print f1.

Lemma f2 : (exists x : E, P x \/ Q x) -> ((exists x : E, P x) \/ (exists x : E, Q x)).
Proof.
	intro.
	elim H.
	intro.
	intro.
	elim H0.
	intro.
	left.
	exists x0.
	assumption.
	intro.
	right.
	exists x0.
	assumption.
Qed.

Print f2.

Lemma f3 : (forall x : E, P x) /\ (forall x : E, Q x) -> (forall x : E, P x /\ Q x).
Proof.
	intros.
	elim H.
	intros.
	split.
	apply H0.
	apply H1.
Qed.

Print f3.

Lemma f4 : (forall x : E, P x /\ Q x) -> ((forall x : E, P x) /\ (forall x : E, Q x)).
Proof.
	intros.
	split.
	intro.
	apply H.
	intro.
	apply H.
Qed.

Lemma f5 : (forall x : E, ~ P x) -> ~(exists x : E, P x).
Proof.
	intro.
	intro.
	elim H0.
	intros.
Qed.

(* Require Export Classical. *)