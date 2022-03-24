<?php
	namespace App\Controller;

	use App\Entity\Recipe;
	use App\Repository\RecipeRepository;
	use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
	use Symfony\Component\HttpKernel\Attribute\AsController;

	
	class RecipeController extends AbstractController{
		public function search(String $name, String $author, String $difficulty, String $ingredient, String $price) : Array{
            $em = $this->getDoctrine()->getManager();
            
            $dql = "SELECT r
		            FROM App\Entity\Recipe r
					JOIN App\Entity\User u
					WHERE 1 = 1";
					
			if($name != "undefined")
			{
				$dql .= "AND LOWER(r.name) LIKE LOWER(:name)";
			}
			
			if($difficulty != "undefined")
			{
				$dql .= "AND r.difficulty <= :difficulty";
			}
			
			if($author != "undefined")
			{
				$dql .= "AND LOWER(u.nickname) LIKE LOWER(:user)";
			}
			
			if($price != "undefined")
			{
				$dql .= "AND :price <= (SELECT SUM(i1.price) FROM App\Entity\Ingredient i1 WHERE i1.recipe = r.id)";
			}
			
			if($ingredient != "undefined")
			{
				$dql .= "AND EXISTS (SELECT i2.id FROM App\Entity\Ingredient i2 WHERE i2.recipe = r.id AND LOWER(i2.name) LIKE LOWER(:ingredient))";
			}
					
			$dql .= "ORDER BY r.name";
            
            
            
            $query = $em->createQuery($dql);
            
            if($name != "undefined")
			{
           		$query->setParameter("name", "%" . $name . "%");
			}
			
			if($difficulty != "undefined")
			{
            	$query->setParameter("difficulty", $difficulty);
			}
			
			if($author != "undefined")
			{
            	$query->setParameter("user", "%" . $author . "%");
			}
			
			if($price != "undefined")
			{
            	$query->setParameter("price", $price);
			}
			
			if($ingredient != "undefined")
			{
            	$query->setParameter("ingredient", "%" . $ingredient . "%");
			}

			return $query->getResult();
		}

	}
?>
