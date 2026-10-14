#include <stdio.h>
#define TAM 3

void vetganhos(int* a, int* mes, int valor){
    if (*mes < TAM) {
        a[*mes] = valor;
        (*mes)++;
    } else {
        printf("Vetor de ganhos está cheio.\n");
    }
}

void vetgastos(int* a, int* mes, int valor){
    if (*mes < TAM) {
        a[*mes] = valor;
        (*mes)++;
    } else {
        printf("Vetor de gastos está cheio.\n");
    }
}

void vetlucros(int* a, int* b, int* c){
    for (int i = 0; i < TAM; i++){
        c[i] = a[i] - b[i];
    }
}

void exibir(int* a){
    for (int i = 0; i < TAM; i++){
        printf("%d ", a[i]);
    }
    printf("\n");
}

void total(int* a){
    float soma = 0;
    for(int i = 0; i < TAM; i++){
        soma += a[i];
    }
    printf("%.2f \n", soma);
}

int lucromedio(int* a){
    float soma = 0, lm;
    for(int i = 0; i < TAM; i++){
        soma += a[i];
    }
    lm = soma/TAM;
    printf("%.2f \n", lm);
    return lm;
}

void abaixodolucro(int* a, float lm){
    for(int i = 0;i < TAM; i++){
        if(a[i] < lm){
            printf("O mês %d está abaixo do lucro médio do ano \n", i + 1);
        }
    }
}

int main(){
    int a[TAM], b[TAM], c[TAM];
    int mes_ganhos = 0, mes_gastos = 0, valor;
    int cont = 0;
    float lm;

    do {
        printf("Lucros do mês %d: \n", cont + 1);
        scanf("%d", &valor);
        vetganhos(a, &mes_ganhos, valor);
        cont++;
    } while (cont < TAM);

    cont = 0; 
    do {
        printf("Gastos do mês %d: \n", cont + 1);
        scanf("%d", &valor);
        vetgastos(b, &mes_gastos, valor);
        cont++;
    } while (cont < TAM);

    vetlucros(a, b, c);

    printf("\nGanhos do ano: \n");
    exibir(a);

    printf("Gastos do ano: \n");
    exibir(b);

    printf("Lucros do ano: \n");
    exibir(c);

    printf("Total de ganhos no ano: \n");
    total(a);

    printf("Total de gastos no ano: \n");
    total(b);

    printf("Total de lucro no ano: \n");
    total(c);

    printf("Lucro médio mensal: \n");
    lucromedio(c);
    
    lm = lucromedio(c);
    abaixodolucro(c, lm);

    return 0;
}