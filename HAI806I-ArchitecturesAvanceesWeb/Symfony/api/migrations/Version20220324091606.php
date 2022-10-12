<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220324091606 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE ingredient (id INT AUTO_INCREMENT NOT NULL, recipe_id INT DEFAULT NULL, name VARCHAR(255) NOT NULL, quantity DOUBLE PRECISION NOT NULL, unit VARCHAR(255) NOT NULL, price DOUBLE PRECISION NOT NULL, INDEX IDX_6BAF787059D8A214 (recipe_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE `utf8_general_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE opinion (id INT AUTO_INCREMENT NOT NULL, recipe_id INT DEFAULT NULL, user_id INT DEFAULT NULL, score INT NOT NULL, comments VARCHAR(255) NOT NULL, date DATE NOT NULL, INDEX IDX_AB02B02759D8A214 (recipe_id), INDEX IDX_AB02B027A76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE `utf8_general_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE recipe (id INT AUTO_INCREMENT NOT NULL, user_id INT DEFAULT NULL, name VARCHAR(255) NOT NULL, quantity_person INT NOT NULL, cooking_mode VARCHAR(255) NOT NULL, difficulty INT NOT NULL, INDEX IDX_DA88B137A76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE `utf8_general_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE step (id INT AUTO_INCREMENT NOT NULL, recipe_id INT DEFAULT NULL, description VARCHAR(255) NOT NULL, number INT NOT NULL, INDEX IDX_43B9FE3C59D8A214 (recipe_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE `utf8_general_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) NOT NULL, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET UTF8 COLLATE `utf8_general_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE ingredient ADD CONSTRAINT FK_6BAF787059D8A214 FOREIGN KEY (recipe_id) REFERENCES recipe (id)');
        $this->addSql('ALTER TABLE opinion ADD CONSTRAINT FK_AB02B02759D8A214 FOREIGN KEY (recipe_id) REFERENCES recipe (id)');
        $this->addSql('ALTER TABLE opinion ADD CONSTRAINT FK_AB02B027A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE recipe ADD CONSTRAINT FK_DA88B137A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE step ADD CONSTRAINT FK_43B9FE3C59D8A214 FOREIGN KEY (recipe_id) REFERENCES recipe (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE ingredient DROP FOREIGN KEY FK_6BAF787059D8A214');
        $this->addSql('ALTER TABLE opinion DROP FOREIGN KEY FK_AB02B02759D8A214');
        $this->addSql('ALTER TABLE step DROP FOREIGN KEY FK_43B9FE3C59D8A214');
        $this->addSql('ALTER TABLE opinion DROP FOREIGN KEY FK_AB02B027A76ED395');
        $this->addSql('ALTER TABLE recipe DROP FOREIGN KEY FK_DA88B137A76ED395');
        $this->addSql('DROP TABLE ingredient');
        $this->addSql('DROP TABLE opinion');
        $this->addSql('DROP TABLE recipe');
        $this->addSql('DROP TABLE step');
        $this->addSql('DROP TABLE user');
    }
}
