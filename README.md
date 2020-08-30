# Agibank API
> API para Processamento de Arquivo .dat da Prova Agibank.

O Projeto busca atender aos requisitos solicitados na prova Agibank.
É realizado a importação do arquivo com formato .dat em %HOMEPATH%/data/in e em seguida a análise do conteúdo do arquivo, 
coletando informações sobre os vendedores, clientes e vendas e extraindo as informações solicitadas em um arquivo .done.dat 
localizado em %HOMEPATH%/data/out

![](../header.png)

## Tecnologia e Versões

* SpringBoot 2.3.3
* JUnit 5
* Docker
* Java 8
* Lombok

## Instruções para execução

* Necessário ter arquivo em <code>%HOMEPATH%/data/in</code> com formato .dat para sucesso na execução.

**Criar imagem docker:**
* Para criar uma imagem com o plugin do maven execute: mvn package.
**Visualizando imagem:**
* docker images
**Executando container
**docker run -p 8080:8080 alissoncostax/spring-docker-spotify:0.0.1-SNAPSHOT

* O resultado do processamento será um arquivo no formato <code>.done.dat</code> localizado em <code>%HOMEPATH%/data/out</code> 

## Histórico de lançamentos

* 1.0.0
    * Criação do Projeto
