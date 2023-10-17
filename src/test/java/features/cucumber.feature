# language: pt

Funcionalidade: Aprender Cucumber
  Como um aluno
  Eu quero aprender a utilizar Cucumber
  Para que eu possa automatizar critérios de aceitação

  Cenário: Deve calcular atraso na entrega
    Dado que a entrega é dia 05/04/2023
    Quando a entrega atrasar em 2 dias
    Então a entrega será efetuada em 07/04/2023

  Cenário: Deve calcular atraso na entrega na China
    Dado que a entrega é dia 05/04/2023
    Quando a entrega atrasar em 2 meses
    Entao a entrega será efetuada em 05/06/2023

  Cenário: Calcular pontos após vitória
    Dado que os pontos antes da vitória são 35
    Quando ganhar 42 pontos pela vitória
    Entao a quantidade de pontos será 75

