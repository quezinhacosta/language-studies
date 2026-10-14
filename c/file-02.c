#include <stdio.h>
#define TAM 3

//corrijido
void armazenar(int* a, int* posicao, int valor){ //tirei o for pq ele vai colocar o primeiro valor inserido pelo usuario para cada posicao
    a[*posicao] = valor;
    (*posicao)++;
}

int soma(int* a, int quant){
    int somar = 0;
    for(int i = 0; i < quant; i++){ //quant não é ponteiro, mas um valor q reflete o tamanho de posiçoes preenchidas
        somar += a[i];
    }
    return somar;
    printf("%d ", somar);
}

void exibir(int* a){
    for(int i = 0; i < TAM; i++){
        printf("%d ", a[i]);
    }
    printf("\n");
}

void calcular(int* a, int* b){
    int quant = TAM; //retirei 'sa' e 'sb'
    int downloand2023 = soma(a, quant), downloand2022 = soma(b, quant); //mudei para que 'downloand' receba a função de soma diretamente
    for(int i = 0; i < TAM; i++){
        if(a[i]==b[i]){
            printf(" no mes %d os valores são iguais \n", i+1);
        }
        else if(a[i] > b[i]){
            //deixei os textos dos prints mais claros
            printf("comparando os anos, o mes %d foi o maior p/ o ano de 2023 \n", i + 1);
            
        }
        else {
            printf("comparando os anos, o mes %d foi o maior p/ o ano de 2022 \n", i + 1);
            
        }
    }

    printf("A soma do ano de 2023: %d\n", downloand2023);
    printf("A soma do ano de 2022: %d\n", downloand2022);
}

int main(){
    int a[TAM], b[TAM], cont = 0, posicao = 0;
    int quant =  TAM, valora, valorb;

    printf("Valores de 2023: \n");
    do{
        printf("Valor do mês %d: ", cont + 1);
        scanf("%d", &valora);
        armazenar(a, &posicao, valora);
        cont++;
    } while(cont < TAM);
    exibir(a);

    cont=0;
    posicao=0;
    do{
        printf("Valor do mês %d: ", cont + 1);
        scanf("%d", &valorb);
        armazenar(b, &posicao, valorb);
        cont++;
    } while(cont < TAM);
    exibir(b);

    printf("A soma do ano de 2023: ");
    soma(a, quant);// tirei o & comercial
    printf("\n");
    printf("A soma do ano de 2022: ");
    soma(b, quant);// tirei o & comercial
    printf("\n");

    calcular(a,b);
    return 0;
}