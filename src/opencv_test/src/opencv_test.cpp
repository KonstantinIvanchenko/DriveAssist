#include "stdio.h"

#include "opencv/cv.h"
#include "opencv/highgui.h"

#include "test.h"

using namespace cv;

int main( int argc, char** argv )
{
	foo1(0);

  Mat image;
  if(argc != 1)
	  image = imread( argv[1], 1 );

  if( argc != 2 || !image.data )
	{
	  printf( "No image data \n" );
	  return -1;
	}

  namedWindow( "Display Image", CV_WINDOW_AUTOSIZE );
  imshow( "Display Image", image );

  waitKey(0);

  return 0;
}

