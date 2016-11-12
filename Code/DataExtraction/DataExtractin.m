clear

rng('default');
% data_path = '..\data\';

%reading training data
x_train = dataset('file','training.csv', 'Delimiter',',');
x_train.Depth = strcmp(x_train.Depth,'Topsoil');
y_train = single(x_train(:,end-4:end));
x_train = single(x_train(:,2:end-5));

%reading test data
x_tst = dataset('file','sorted_test.csv', 'Delimiter',',');
x_tst.Depth = strcmp(x_tst.Depth,'Topsoil');
tst_id = x_tst.PIDN;
x_tst = single(x_tst(:,2:end));

save('DataMat.mat')