function [x_1, x_2]=prep(x)
w = 0.54 - 0.46*cos(2*pi*(0:7)/15);
ham_w   = [w w(end:-1:1)];           % 16-tap hamming windows
ham_w   = ham_w / sum(ham_w);
x_step = smooth0(ham_w, x', 8)';    % 0 delay smoothing
x_step = x_step(:, 1:8:end);
x_step = x_step(:, [1:40 100:end]);

x_1 = (x_step - 0.8)/0.2;

x_2  = (diff(x_step')')/0.015;
w_std = std(x_2);
w_std = (w_std - min(w_std))/max(w_std);
x_2 = bsxfun(@times,x_2,w_std);
end