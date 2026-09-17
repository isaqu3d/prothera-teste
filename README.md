# Teste Prático — Java

Gerenciamento de funcionários de uma indústria, resolvendo os requisitos do teste com Collections, Streams e `BigDecimal`.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

---

## 🚀 Sobre

Aplicação console desenvolvida como **teste prático de programação**. A partir de uma lista fixa de funcionários, o programa executa todas as operações pedidas no enunciado: remoção, reajuste salarial, agrupamento por função, filtro por mês de aniversário, ordenação alfabética, totalização e cálculo de salários mínimos.

A saída usa formatação brasileira — datas em `dd/MM/yyyy` e valores com ponto no milhar e vírgula no decimal (`19.119,88`).

---

## 🛠 Tecnologias

| Tecnologia          | Por quê                                                            |
| ------------------- | ------------------------------------------------------------------ |
| **Java 21**         | Streams, `Optional.ifPresentOrElse`, `Stream.toList()`             |
| **Maven**           | Gerenciamento do projeto e build                                   |
| **`java.time`**     | `LocalDate` e `Period` para datas e cálculo de idade               |
| **`BigDecimal`**    | Precisão exata em valores monetários (sem erro de ponto flutuante) |
| **`DecimalFormat`** | Formatação numérica com `Locale` pt-BR fixo                        |

---

## 📁 Estrutura do projeto

```
prothera-teste/
├── pom.xml
└── src/main/java/org/example/
    ├── Principal.java                    # orquestra os requisitos 3.1 a 3.12
    ├── model/
    │   ├── Pessoa.java                   # nome, dataNascimento, getIdade()
    │   └── Funcionario.java              # estende Pessoa + salario, funcao
    ├── service/
    │   └── FuncionarioService.java       # regras de negócio (sem I/O)
    ├── util/
    │   └── FuncionarioFormatter.java     # formatação de data e número pt-BR
    └── view/
        └── FuncionarioPrinter.java       # saída no console
```

### Separação de responsabilidades

```
Principal  →  orquestra e lê como o roteiro do enunciado
    ↓
Service    →  filtra, agrupa, soma, ordena — retorna dados, não imprime
    ↓
Printer    →  recebe dados prontos e escreve no console
    ↓
Formatter  →  converte LocalDate e BigDecimal em String pt-BR
```

A lógica de negócio não conhece `System.out`, o que a torna testável isoladamente.

---

## ⚙️ Como rodar

### Pré-requisitos

- JDK 21+
- IntelliJ IDEA (ou outra IDE com suporte a Maven)

### 1. Clone

```sh
git clone git@github.com:isaqu3d/prothera-teste.git
```

### 2. Abra no IntelliJ

**File → Open** e selecione a pasta do projeto. Aguarde a importação do Maven concluir (indicador no canto inferior direito).

### 3. Execute

Abra `src/main/java/org/example/Principal.java` e clique no ▶ ao lado do método `main`.

O resultado de todos os requisitos aparece no console.

---

## ✅ Requisitos atendidos

| Item     | Descrição                                     | Implementação                                       |
| -------- | --------------------------------------------- | --------------------------------------------------- |
| **1**    | Classe `Pessoa` com nome e data de nascimento | `model/Pessoa.java`                                  |
| **2**    | Classe `Funcionario` estendendo `Pessoa`      | `model/Funcionario.java`                             |
| **3.1**  | Inserir funcionários na ordem da tabela       | `ArrayList` mutável em `Principal`                   |
| **3.2**  | Remover o funcionário "João"                  | `removeIf` por nome                                  |
| **3.3**  | Imprimir com data e valores formatados        | `FuncionarioFormatter` com `Locale` pt-BR            |
| **3.4**  | Aumento de 10% nos salários                   | `multiply` + `setScale(2, HALF_UP)`                  |
| **3.5**  | Agrupar por função em um `Map`                | `Collectors.groupingBy`                              |
| **3.6**  | Imprimir agrupados por função                 | `imprimirAgrupados`                                  |
| **3.7**  | —                                             | Não consta no enunciado (numeração salta 3.6 → 3.8)  |
| **3.8**  | Aniversariantes dos meses 10 e 12             | `filter` por `getMonthValue()`, com varargs          |
| **3.9**  | Funcionário com maior idade                   | `min` por data de nascimento + `Period.between`      |
| **3.10** | Lista em ordem alfabética                     | `sorted(Comparator.comparing(...))`                  |
| **3.11** | Total dos salários                            | `reduce(BigDecimal.ZERO, BigDecimal::add)`           |
| **3.12** | Quantos salários mínimos cada um ganha        | `divide` com escala e arredondamento explícitos      |

---

## 🎯 Decisões técnicas

### `BigDecimal` sempre construído a partir de `String`

`new BigDecimal(2009.44)` guarda `2009.4399999999999977...` porque `double` é binário. Com `String`, o valor e a escala são preservados exatamente — essencial para dinheiro.

### `setScale` obrigatório após `multiply` e `divide`

`multiply` soma as escalas dos operandos, então `2009.44 × 1.10` resulta em 4 casas decimais. Sem `setScale(2, HALF_UP)`, centavos fantasmas se propagam até o total. No `divide` é ainda mais crítico: sem escala definida, uma dízima periódica lança `ArithmeticException`.

### `Locale` fixo em pt-BR

`DecimalFormat` sem `DecimalFormatSymbols` explícito usa o locale da máquina — o mesmo código imprimiria `2,009.44` num ambiente en-US. Fixar o locale torna a saída determinística em qualquer máquina.

### Maior idade via `min` por data, não `max` por idade

O mais velho é quem tem a data de nascimento **menor**. Comparar pela data (e não pela idade em anos inteiros) também desempata corretamente duas pessoas nascidas no mesmo ano.

### `Period.between` para calcular a idade

Considera mês e dia, não apenas a diferença entre os anos — quem ainda não fez aniversário no ano corrente tem um ano a menos.

### Ordenação alfabética e acentuação

`String.compareTo` ordena por código Unicode, o que pode divergir da ordem de dicionário em nomes acentuados. Para esta lista o resultado coincide; em um cenário real, `Collator` com locale pt-BR seria a escolha correta.

---

Feito por [Isaque de Sousa](https://github.com/isaqu3d)