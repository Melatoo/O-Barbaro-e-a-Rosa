# iBrary

## Introdução

Bem-vindo ao repositório do Jogo O Bárbaro e a Rosa. Este projeto tem como foco criar um jogo no estilo Adventure(texto).
Nesse jogo, o jogador será um bárbaro em um mundo de fantasia medieval. O jogador irá se movimentar entre os diferentes reinos e ambientes por meio de trilhas em busca de uma rosa que libertará seus amigos, essa rosa está perdida em algum local do mundo. Ao chegar no ambiente onde o bárbaro julga estar a rosa, ele deverá usar a única poção mágica reveladora que carrega em sua tanga para revelar a rosa mágica e recuperá-la. O jogador vencerá o jogo somente se conseguir recuperar a rosa.


## Como funciona?

- O mapa do mundo é mostrado na figura abaixo. Cada ambiente, além das trilhas para os demais ambientes, pode conter itens (rosa, hambúrguer, machado e dica). Neste jogo, o bárbaro começa com uma quantidade de energia, e cada trilhas que ele segue gasta 1 energia, haverá um tesouro enterrado no ambiente A, três dicas com o texto “O tesouro não está no(a) X”, uma dica com o texto “O tesouro está próximo ao(à) Y”,um hambúrguer que recupera 20 de energia e um machado que evita que o jogador gaste energia ao batalhar. Como o mundo é uma fantasia medieval, às vezes, algumas trilhas terão monstros no caminho, que o bárbaro gastará 1 de energia para derrotá-los. Os valores de X's, Y e Z (inteiro entre 1 e 12) devem ser definidos aleatoriamente no início do jogo.

- A localização da rosa (A), das dicas e dos itens devem ser aleatoriamente definidos no início do jogo e armazenados em um arquivo texto (que serve como um gabarito do jogo). Além disso, cada trilhas da casa pode estar no estado “sem monstros” ou “infestada”, sendo que esse estado deve ser aleatoriamente redefinido a cada vez que o bárbaro passar por uma trilha. Ou seja, ao iniciar o jogo e após cada tentativa do bárbaro de abrir uma porta, o estado das trilhas deve ser aleatoriamente redefinido. A quantidade de energia define a duração máxima do jogo. Esse valor deve ser um número inteiro entre 20 e 50 aleatoriamente definido no início do jogo e apresentado para o jogador. Ao longo do jogo, a única maneira de recuperar energia é encontrando o hambúrguer. Nesse caso, a energia do bárbaro é incrementada em 20, independente da energia anterior. Se o bárbaro não recuperar o tesouro antes de ficar sem energia ou se utilizar sua poção mágica reveladora no local errado (onde não está a rosa), o jogador perderá o jogo e o mesmo terminará.

- No jogo proposto o jogador sempre começará o jogo no reino de Ghanor. Além disso, a geração da dica com o texto “O tesouro está próximo ao(à) Y” será realizada escolhendo-se aleatoriamente um ambiente (vizinho) que tenha uma trilha de passagem para o ambiente onde o tesouro se localiza. Por fim, o jogo terá uma interface gráfica como a apresentada na figura abaixo.

## Tecnologias Utilizadas

- Java

### Integrantes do projeto
- Davi Silveira Siqueira
- Thiago Melato Fonseca
