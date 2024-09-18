package visuals.bars;

import java.awt.Color;

import main.ArrayVisualizer;
import utils.Highlights;
import utils.Renderer;
import visuals.Visual;

/*
 * 
MIT License

Copyright (c) 2020 MusicTheorist
Copyright (c) 2021 ArrayV 4.0 Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 *
 */

final public class SineWave extends Visual {

    public SineWave(ArrayVisualizer ArrayVisualizer) {
        super(ArrayVisualizer);
    }
	
	private final int X_MARGIN = 40;
	private final int Y_OFFSET = 70;
	
	private final double B_SCALE = 1.05;
	
	private void bubble(ArrayVisualizer ArrayVisualizer, Highlights Highlights, int[] array, int N, int xa, int xb, int y, int a, int b) {
		int s = (int)((xb-xa) * B_SCALE);
		
		if(s < 2) return;
		
		int x = (xa+xb)/2, n = b-a, la = 90;
		
		for(int i = a; i < b; i++) {
			int la1 = 90 - (int)(360d/n * (i-a+1));
			
			if(la1 == la) continue;
			
			this.mainRender.setColor(Color.WHITE);
			
			if(!Highlights.containsPosition(i))
				this.mainRender.setColor(getIntColor(array[i], N));
			
			if(Highlights.fancyFinishActive() && i < Highlights.getFancyFinishPosition())
				this.mainRender.setColor(Color.GREEN);
			
			this.mainRender.fillArc(x-s/2, y-s/2, s, s, la, la1-la);
			la = la1;
		}
	}
	
	private void bubble2(ArrayVisualizer ArrayVisualizer, Highlights Highlights, int[] array, int N, int xa, int xb, int y0, int a, int b) {
		int r = (int)((xb-xa) / 2d * B_SCALE);
		
		if(r < 1) return;
		
		int n = b-a;
		double is = (double)N/n;
		
		int[] x = {xb-r, 0, 0};
		int[] y = {y0, 0, 0};
		
		double disp = (1 + Math.cos((Math.PI * (array[b-1] - (N-1))) / (N * 0.5))) * 0.5;
		x[2] = x[0] + (int)(disp * r * Math.cos(Math.PI * (2d*(n-1) / n - 0.5)));
		y[2] = y[0] + (int)(disp * r * Math.sin(Math.PI * (2d*(n-1) / n - 0.5)));
		
		for(int i = 0; i < n; i++) {
			x[1] = x[2];
			y[1] = y[2];
			
			disp = (1 + Math.cos((Math.PI * (array[a+i] - (int)(i*is))) / (N * 0.5))) * 0.5;
			x[2] = x[0] + (int)(disp * r * Math.cos(Math.PI * (2d*i / n - 0.5)));
			y[2] = y[0] + (int)(disp * r * Math.sin(Math.PI * (2d*i / n - 0.5)));
			
			if(Highlights.fancyFinishActive() && a+i < Highlights.getFancyFinishPosition())
				this.mainRender.setColor(Color.GREEN);
				
			else if(Highlights.containsPosition(a+i)) {
				this.mainRender.setColor(ArrayVisualizer.getHighlightColor());
				this.extraRender.drawPolygon(x, y, 3); 
			}
			else if(ArrayVisualizer.colorEnabled()) 
				this.mainRender.setColor(getIntColor(array[a+i], ArrayVisualizer.getCurrentLength()));
			
			else this.mainRender.setColor(Color.WHITE);
			
			this.mainRender.fillPolygon(x, y, 3); 
		}
		this.extraRender.setStroke(ArrayVisualizer.getDefaultStroke());
	}

    @Override
    public void drawVisual(int[] array, ArrayVisualizer ArrayVisualizer, Renderer Renderer, Highlights Highlights) {
		if(Renderer.auxActive) return;
		
		int w = ArrayVisualizer.windowWidth(),
		    h = ArrayVisualizer.windowHeight() - Y_OFFSET,
		    s = Math.min(w, h) - X_MARGIN,
			y = Y_OFFSET + s/2,
		    N = ArrayVisualizer.getCurrentLength();
			
		int lm = w/2 - s/2;
		int j = 0, lx = lm;
		
		for(int i = 1; i < N; i++) {
			if(array[i] < array[i-1]) {
				int x = lm + (int)((double)i/N * s);
				this.bubble2(ArrayVisualizer, Highlights, array, N, lx, lx = x, y, j, j = i);
			}
		}
		this.bubble2(ArrayVisualizer, Highlights, array, N, lx, lm+s, y, j, N);
    }
}