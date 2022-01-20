###########
# Mémoire #
###########

# Exercice 1

n <- 15
5 -> n
x <- 1
X <- 10
n
x
X
n <- 10 + 2
n
n <- 3 + rnorm(1)
(10 + 2)*5
name <- "Carmen"; n1 <- 10; n2 <- 100; m<- 0.5
ls()

# Exercice 2

M <- data.frame(n1, n2, m)
ls.str(pat = "M")
rm("M", "m", "n", "n1", "n2", "name", "x", "y", "X")

########
# Aide #
########

# Exercice 3

?rm
rm(list = ls())
help(rm)

#############
# Graphique #
#############

# Exercice 4

x11(); x11(); pdf();
dev.list()
dev.cur()
dev.set(3)
dev.cur()
dev.off(2)
dev.list()
dev.off()
dev.off()

# Exercice 6

?plot
?hist
x <- rnorm(1000)
y <- rnorm(1000)
plot(x,y,xlab="Mille valeurs au hasard", ylab="Mille autres valeurs",xlim=c(-2,2), ylim=c(-2,2), pch=22, col="red", bg="yellow", bty="l",tcl=0.4, main="Configurer les graphiques en R", las=1, cex=1.5)
?rnorm

#################
# Loi Binomiale #
#################

# Exercice 7

?pbinom
n <- 18
p <- 1/6

infX <- pbinom(q = 3, size = n, prob = p)
x <- infX - pbinom(q = 2, size = n, prob = p)
supX <- pbinom(q = 3, size = n, prob = p, lower.tail = FALSE) + x
pbinom(q = 16, size = n, prob = p)

# Exercice 8

?pnorm
pnorm(1.41)
pnorm(-2.07)
pnorm(-1.26, lower.tail = FALSE)

qnorm(0.95)
pnorm(1.644854)
qnorm(0.1)
qnorm(0.99, lower.tail = FALSE)

pnorm((-5 - -5) / 4)
pnorm(-5, -5, 4)
pnorm((0 - -5) / 4)
pnorm((5 - -5) / 4, lower.tail = FALSE)

qnorm(0.95) * 4 - 5
qnorm(0.95, -5, 4)
qnorm(0.05, -5, 4)
qnorm(0.01, -5, 4, lower.tail = FALSE)

# Exercice 9

?pchisq
pX <- 15
pY <- 10
pchisq(6.26, pX)
pchisq(3.25, pY, lower.tail = FALSE)
pchisq(11.52, pX + pY, lower.tail = FALSE)

qchisq(0.01, pX)
qchisq(0.05, pX)
qchisq(0.99, pX)

# Exervice 10

?pt
pt(0.408, 5)
qt(0.05, 5)

# Exercice 11 
# TRIVIAL

# Exercice 12

hist(rnorm(100000, -5, 4), probability = TRUE)

points(-19, dnorm(-19, -5, 4))
points(-17, dnorm(-17, -5, 4))
points(-15, dnorm(-15, -5, 4))
points(-13, dnorm(-13, -5, 4))
points(-11, dnorm(-11, -5, 4))
points(-9, dnorm(-9, -5, 4))
points(-7, dnorm(-7, -5, 4))
points(-5, dnorm(-5, -5, 4))
points(-3, dnorm(-3, -5, 4))
points(-1, dnorm(-1, -5, 4))
points(1, dnorm(1, -5, 4))
points(3, dnorm(3, -5, 4))
points(5, dnorm(5, -5, 4))
points(7, dnorm(7, -5, 4))
points(9, dnorm(9, -5, 4))
