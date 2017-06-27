#include <stdio.h>
#include <complex.h>
#include <stdlib.h>
 
 
float complex * recursive_FFT(_Complex float *z, int n){
	if(n==1) return z;
	_Complex float *z0, *z1, *y0, *y1, *y, W, Wn, w=I*(2*3.14/n);
	z0 =(float complex *)malloc(n/2 * sizeof(float complex));
	z1 =(float complex *)malloc(n/2 * sizeof(float complex));
	y0 =(float complex *)malloc(n/2 * sizeof(float complex));
	y1 =(float complex *)malloc(n/2 * sizeof(float complex));
	y=(float complex *)malloc(n * sizeof(float complex));
	Wn = cexp(w);
	W=1;
	for(int i=0;i<n;i++){
	    if(i%2){
	    	z1[i/2]=z[i];
	    }else{
	    	z0[i/2]=z[i];
	    }	
	}
	y0 = recursive_FFT(z0,n/2);
	y1 = recursive_FFT(z1,n/2);
	for(int k=0;k<n/2;k++){
		y[k]=y0[k] + W*y1[k];
		y[k+n/2]=y0[k] - W*y1[k];
		W= W*Wn;
	}
	return y;
}


_Complex float * inverse_FFT(_Complex float *z, int n){
	if(n==1) return z;
	_Complex float *z0, *z1, *y0, *y1, *y, W, Wn, w=I*(-2*3.14/n);
	z0 =(float complex *)malloc(n/2 * sizeof(float complex));
	z1 =(float complex *)malloc(n/2 * sizeof(float complex));
	y0 =(float complex *)malloc(n/2 * sizeof(float complex));
	y1 =(float complex *)malloc(n/2 * sizeof(float complex));
	y=(float complex *)malloc(n * sizeof(float complex));
	Wn = cexp(w);
	W=1;
	for(int i=0;i<n;i++){
	    if(i%2){
	    	z1[i/2]=z[i];
	    }else{
	    	z0[i/2]=z[i];
	    }	
	}
	y0 = inverse_FFT(z0,n/2);
	y1 = inverse_FFT(z1,n/2);
	for(int k=0;k<n/2;k++){
		y[k]=y0[k] + W*y1[k];
		y[k+n/2]=y0[k] - W*y1[k];
		W= W*Wn;
	}
	return y;
}

 
int main(void) {
	int k;
	scanf("%d",&k);
	int n;
	scanf("%d",&n);
	_Complex float *z, *y;
	z = (float complex *)malloc(n * sizeof(float complex));
	y = (float complex *)malloc(n * sizeof(float complex));
	float a,b;
	for(int i=0;i<n;i++){
		scanf("%f",&a);
		scanf("%f",&b);
		*(z+i)= a + I*b;
	}
	if(k==0){
		y = recursive_FFT(z,n);
		printf("%d ",n);
	    for(int i=0;i<n;i++){
		a=round(10*creal(*(y+i)))/10; b= round(10*cimag(*(y+i)))/10;
		if(a!=0) printf("%.1f ",a);
		else printf("0 ");
		if(b!=0) printf("%.1f ",b);
		else printf("0 ");
	    }
	}else{
		y = inverse_FFT(z,n);
		printf("%d ",n);
	    for(int i=0;i<n;i++){
		a=round(10*creal(*(y+i))/n)/10; b= round(10*cimag(*(y+i))/n)/10;
	    if(a!=0) printf("%.1f ",a);
		else printf("0 ");
		if(b!=0) printf("%.1f ",b);
		else printf("0 ");
	    }
	}
	
	return 0;
}