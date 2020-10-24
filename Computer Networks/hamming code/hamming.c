#include <stdio.h>
#include <math.h>

int ham(int check[],int pos,int tot_len)
{
int count=0,i,j;
i=pos-1;
while(i<tot_len)
{
for(j=i;j<i+pos;j++)
{
if(check[j] == 1)
count++;
}
i=i+2*pos;
}
if(count%2 == 0)
{
return 0;
}
else
{
return 1;
}
}

void correct(int check[],int error_pos,int tot_len)
{
int i,a=error_pos-1;
if(check[a]==1)
{
check[a]=0;
}
else
{
check[a]==1;
}
printf("\nThe Corrected data word is: ");
for(i=0;i<tot_len;i++)
{
printf("%d",check[i]);
}
}
void main()
{
int n,i,no_pbits,j,k,tot_len,error_pos=0,pos,val,data[64],check[64];
no_pbits=j=k=0;
clrscr();
printf("Enter the number of data word bits : ");
scanf("%d",&n);
printf("\nEnter the data word:");
for(i=0;i<n;i++)
{
scanf("%1d",&data[i]);
}
for(i=0;n>(int)pow(2,i)-(i+1);i++)
{
no_pbits++;
}
tot_len= no_pbits + n;
for(i=0;i<tot_len;i++)
{
if(i==((int)pow(2,k)-1))
{
check[i]=0;
k++;
}
else
{
check[i]=data[j];
j++;
}
}
for(i=0;i<no_pbits;i++)
{
pos= (int)pow(2,i);
val= ham(check,pos,tot_len);
check[pos-1]=val;
}
printf("\nTransmitted data word is: ");
for(i=0;i<tot_len;i++)
{
printf("%d",check[i]);
}
printf("\n\nEnter the data word received: ");
for(i=0;i<tot_len;i++)
scanf("%1d",&check[i]);
for(i=0;i<no_pbits;i++)
{
pos= (int)pow(2,i);
val= ham(check,pos,tot_len);
if(val != 0)
error_pos+=pos;
}
if(error_pos == 0)
printf("Received data word is correct.\n");
else
{
printf("Error detected at bit position: %d\n",error_pos);
correct(check,error_pos,tot_len);
}  
}
